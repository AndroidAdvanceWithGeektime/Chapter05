package com.sample.startup.gc.utils;

import android.text.TextUtils;

import com.sample.startup.gc.info.CpuStat;
import com.sample.startup.gc.info.LoadAvg;
import com.sample.startup.gc.info.TaskStat;

import java.util.ArrayList;

public class SystemUtils {

	private static final String TAG = "SystemUtils";

	/**
	 * 获取CPU核心数
	 */
	public static int getCpuCorecCount() {
		// 输出结果：0-7
		String s = ShellHelper.executeShellCommand("cat /sys/devices/system/cpu/possible");
		return Integer.parseInt(s.split("-")[1].replace("\n", "")) + 1;
	}

	/**
	 * 获取负载
	 */
	public static LoadAvg getLoadAvg() {
		String s = ShellHelper.executeShellCommand("cat /proc/loadavg");
		return new LoadAvg(s.split(" "));
	}

	/**
	 * 获取CPU状态
	 */
	public static CpuStat getCpuStat() {
		String s = null;
		for (; ; ) {
			s = ShellHelper.executeShellCommand("cat /proc/stat");
			if (!TextUtils.isEmpty(s)) {
				break;
			}
		}
		String cpuinfo = s.split("\n")[0];
		String[] split = cpuinfo.split("  ")[1].split(" ");
		return new CpuStat(split);
	}

	public static ArrayList<CpuStat> getAllCpuStat() {

		ArrayList<CpuStat> result = new ArrayList<>();

		int cpuCorecCount = getCpuCorecCount();

		String s = ShellHelper.executeShellCommand("cat /proc/stat");
		String[] split = s.split("\n");

		for (int i = 1; i <= cpuCorecCount; i++) {
			// 替换掉"cpu0 "这个字符串
			String cpuName = "cpu" + (i - 1) + " ";
			String a = split[i].replace(cpuName, "");
			result.add(new CpuStat(a.split(" ")));
		}

		return result;

	}

	/**
	 * 获取指定进程的状态
	 */
	public static TaskStat getTaskStat(String pid) {
		String s;
		for (; ; ) {
			s = ShellHelper.executeShellCommand("cat /proc/" + pid + "/stat");
			if (!TextUtils.isEmpty(s)) {
				break;
			}
		}

		return new TaskStat(s.replace("\n", "").split(" "));
	}

	/**
	 * 获取指定进程中所有线程的状态
	 */
	public static ArrayList<TaskStat> getThreadTaskStat(String pid) {
		ArrayList<TaskStat> datas = new ArrayList<>();
		String s;

		for (; ; ) {
			s = ShellHelper.executeShellCommand("ls /proc/" + pid + "/task");
			if (!TextUtils.isEmpty(s)) {
				break;
			}
		}

		String[] split = s.split("\n");
		for (String threadId : split) {
			String threadStr;

			for (; ; ) {
				threadStr = ShellHelper.executeShellCommand("cat /proc/" + pid + "/task/" + threadId + "/stat");
				if (!TextUtils.isEmpty(threadStr)) {
					break;
				}
			}

			TaskStat taskStat = new TaskStat(threadStr.replace("\n", "").split(" "));
			datas.add(taskStat);
		}
		return datas;
	}

}
