package com.sample.startup.gc;


import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.sample.startup.gc.info.CpuStat;
import com.sample.startup.gc.info.CurrentStat;
import com.sample.startup.gc.info.ResultInfo;
import com.sample.startup.gc.info.TaskStat;
import com.sample.startup.gc.utils.SystemUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ProcessCpuTracker {

	private static final String TAG = "ProcessCpuTracker";

	// /proc/self/stat
	private static final int PROCESS_STATS_STATUS = 2;
	private static final int PROCESS_STATS_MINOR_FAULTS = 9;
	private static final int PROCESS_STATS_MAJOR_FAULTS = 11;
	private static final int PROCESS_STATS_UTIME = 13;
	private static final int PROCESS_STATS_STIME = 14;

	// /proc/self/sched
	private static final String NR_VOLUNTARY_SWITCHES = "nr_voluntary_switches";
	private static final String NR_INVOLUNTARY_SWITCHES = "nr_involuntary_switches";
	private static final String SE_IOWAIT_COUNT = "se.statistics.iowait_count";
	private static final String SE_IOWAIT_SUM = "se.statistics.iowait_sum";

	// /proc/stat
	private static final int SYSTEM_STATS_USER_TIME = 2;
	private static final int SYSTEM_STATS_NICE_TIME = 3;
	private static final int SYSTEM_STATS_SYS_TIME = 4;
	private static final int SYSTEM_STATS_IDLE_TIME = 5;
	private static final int SYSTEM_STATS_IOWAIT_TIME = 6;
	private static final int SYSTEM_STATS_IRQ_TIME = 7;
	private static final int SYSTEM_STATS_SOFT_IRQ_TIME = 8;

	// /proc/loadavg
	private static final int LOAD_AVERAGE_1_MIN = 0;
	private static final int LOAD_AVERAGE_5_MIN = 1;
	private static final int LOAD_AVERAGE_15_MIN = 2;

	// How long a CPU jiffy is in milliseconds.
	private final long mJiffyMillis;
	private int mCurrentProcID;


	public ProcessCpuTracker(int id) {
		long jiffyHz = Sysconf.getScClkTck();
		mJiffyMillis = 1000 / jiffyHz;
		mCurrentProcID = id;
	}

	private CurrentStat currentStat;

	ResultInfo resultInfo = new ResultInfo();

	public void update() {

		CurrentStat lastCurrentStat = null;

		if (currentStat == null) {
			currentStat = new CurrentStat();
		} else {
			lastCurrentStat = currentStat.clone();
		}

		currentStat.clear();

		currentStat.setTime(System.currentTimeMillis());
		currentStat.setCpuStat(SystemUtils.getCpuStat());
		currentStat.setCpuStatsList(SystemUtils.getAllCpuStat());
		currentStat.setMainTaskStat(SystemUtils.getTaskStat(mCurrentProcID + ""));
		currentStat.setThreadTaskStat(SystemUtils.getThreadTaskStat(mCurrentProcID + ""));

		if (lastCurrentStat == null) {
			return;
		}

		CurrentStat gap = CurrentStat.gap(currentStat, lastCurrentStat);

		resultInfo = new ResultInfo();
		resultInfo.setTime(getTime(lastCurrentStat.getTime(), currentStat.getTime()));
		resultInfo.setSystemTotal(gap.getCpuStat().getSystemInfo(mJiffyMillis));
		resultInfo.setCpuCore("CPU Core: " + SystemUtils.getCpuCorecCount());
		resultInfo.setLoad(SystemUtils.getLoadAvg().toString());
		resultInfo.setMainProcess(getMainInfo(lastCurrentStat, currentStat));
		resultInfo.setThreadProcess(getThreadInfo(lastCurrentStat, currentStat));

	}

	private String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "HH:mm:ss.SSS";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds)));
	}

	private String getTime(long t1, long t2) {
		String s1 = timeStamp2Date(t1 + "", null);
		String s2 = timeStamp2Date(t2 + "", null);
		long gapTime = Math.abs(t1 - t2);
		if (t1 > t2) {
			return "usage: CPU usage " + gapTime + "ms(from " + s2 + " to " + s1 + "):";
		} else {
			return "usage: CPU usage " + gapTime + "ms(from " + s1 + " to " + s2 + "):";
		}


	}

	private String getMainInfo(CurrentStat oldCurrentStat, CurrentStat newCurrentStat) {

		int gapFaults = newCurrentStat.getMainTaskStat().getCminflt() - oldCurrentStat.getMainTaskStat().getCminflt();

		String mainPid = oldCurrentStat.getMainTaskStat().getPid();
		String mainName = oldCurrentStat.getMainTaskStat().getComm();
		String mainStat = oldCurrentStat.getMainTaskStat().getState();

		CpuStat gapCpuStat = CpuStat.gapCpuStat(newCurrentStat.getCpuStat(), oldCurrentStat.getCpuStat());
		String userRate = gapCpuStat.getUserRate();
		String mainCpuRate = gapCpuStat.getCpuRate();
		String kernel = gapCpuStat.getSystemRate();

		return mainCpuRate + "% " + mainPid + "/" + mainName + "(" + mainStat + "):" + userRate + "% user + " + kernel + "% kernel faults:" + gapFaults;

	}

	private ArrayList<String> getThreadInfo(CurrentStat oldCurrentStat, CurrentStat newCurrentStat) {

		ArrayList<String> result = new ArrayList<>();

		try {

			ArrayList<CpuStat> oldCpuStatsList = oldCurrentStat.getCpuStatsList();
			ArrayList<CpuStat> newCpuStatsList = newCurrentStat.getCpuStatsList();

			ArrayList<TaskStat> threadTaskStat = newCurrentStat.getThreadTaskStat();

			ArrayList<TaskStat> newCurrentStatThreadTaskStat = newCurrentStat.getThreadTaskStat();
			ArrayList<TaskStat> oldCurrentStatThreadTaskStat = oldCurrentStat.getThreadTaskStat();

			for (int i = 0; i < oldCurrentStatThreadTaskStat.size(); i++) {

				String threadCpuRate = "";
				String threadUserRate = "";
				String threadKernel = "";

				String threadPid = "";
				String threadName = "";
				String threadState = "";

				int gapFaults = 0;

				for (int j = 0; j < newCurrentStatThreadTaskStat.size(); j++) {

					if (TextUtils.equals(oldCurrentStatThreadTaskStat.get(i).getComm(),
							newCurrentStatThreadTaskStat.get(i).getComm())) {

						TaskStat taskStat = threadTaskStat.get(i);

						// 当前线程使用的是哪一个CPU
						int cpuProcessor = taskStat.getProcessor();

						if (cpuProcessor == -1) {
							cpuProcessor = 0;
						}

						CpuStat gapCpuStat = CpuStat.gapCpuStat(newCpuStatsList.get(cpuProcessor), oldCpuStatsList.get(cpuProcessor));
						threadCpuRate = gapCpuStat.getCpuRate();
						threadUserRate = gapCpuStat.getUserRate();
						threadKernel = gapCpuStat.getSystemRate();

						threadPid = taskStat.getPid();
						threadName = taskStat.getComm();
						threadState = taskStat.getState();

						gapFaults = newCurrentStat.getThreadTaskStat().get(i).getCminflt() - oldCurrentStat.getThreadTaskStat().get(i).getCminflt();

					}

				}

				result.add(threadInfo(threadCpuRate, threadPid, threadName, threadState, threadUserRate, threadKernel, gapFaults + ""));

			}
		} catch (Exception e) {
			Log.e(TAG, "getThreadInfo: " + e.getMessage());
		}
		return result;

	}

	private String threadInfo(String mainCpuRate, String mainPid, String mainName, String mainStat, String userRate, String kernel, String gapFaults) {
		return mainCpuRate + "% " + mainPid + "/" + mainName + "(" + mainStat + "):" + userRate + "% user + " + kernel + "% kernel faults:" + gapFaults;
	}

	@SuppressLint("SimpleDateFormat")
	final public String printCurrentState(long now) {

		if (resultInfo == null) {
			return "";
		}
		String str = "";
		ArrayList<String> threadProcess = resultInfo.getThreadProcess();
		if (threadProcess != null) {
			for (int i = 1; i < threadProcess.size(); i++) {
				str += "  " + threadProcess.get(i) + "\n";
			}
		}

		return resultInfo.getTime() + "\n" +
				resultInfo.getSystemTotal() + "\n" +
				resultInfo.getCpuCore() + "\n" +
				resultInfo.getLoad() + "\n" +
				"\n" +
				"Process:com.sample.startup\n" +
				"  " + resultInfo.getMainProcess() + "\n" +
				"\n" +
				"Threads:\n" +
				str;
	}


}
