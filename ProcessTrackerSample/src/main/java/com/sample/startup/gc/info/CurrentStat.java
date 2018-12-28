package com.sample.startup.gc.info;

import java.util.ArrayList;

public class CurrentStat {

	private static final String TAG = "CurrentStat";

	private long time;
	private CpuStat cpuStat;
	private ArrayList<CpuStat> cpuStatsList;
	private TaskStat mainTaskStat;
	private ArrayList<TaskStat> threadTaskStat;

	public CurrentStat() {
	}

	public CurrentStat(long time, CpuStat cpuStat, TaskStat mainTaskStat, ArrayList<TaskStat> threadTaskStat) {
		this.time = time;
		this.cpuStat = cpuStat;
		this.mainTaskStat = mainTaskStat;
		this.threadTaskStat = threadTaskStat;
	}

	public CurrentStat(long time, CpuStat cpuStat, ArrayList<CpuStat> cpuStatsList, TaskStat mainTaskStat, ArrayList<TaskStat> threadTaskStat) {
		this.time = time;
		this.cpuStat = cpuStat;
		this.cpuStatsList = cpuStatsList;
		this.mainTaskStat = mainTaskStat;
		this.threadTaskStat = threadTaskStat;
	}

	/**
	 * 求两个currentStat的差距
	 */
	public static CurrentStat gap(CurrentStat currentStat1, CurrentStat currentStat2) {

		CurrentStat result;

		long time1 = currentStat1.getTime();
		long time2 = currentStat2.getTime();

		if (time1 > time2) {
			result = hideGap(currentStat1, currentStat2);
		} else {
			result = hideGap(currentStat2, currentStat1);
		}

		return result;
	}

	private static CurrentStat hideGap(CurrentStat newStat, CurrentStat oldStat) {

		long gapTime = newStat.getTime() - oldStat.getTime();

		CurrentStat result = new CurrentStat();

		result.setTime(gapTime);
		CpuStat gapCpuStat = CpuStat.gapCpuStat(newStat.getCpuStat(), oldStat.getCpuStat());
		result.setCpuStat(gapCpuStat);

		return result;

	}

	public ArrayList<CpuStat> getCpuStatsList() {
		return cpuStatsList;
	}

	public void setCpuStatsList(ArrayList<CpuStat> cpuStatsList) {
		this.cpuStatsList = cpuStatsList;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public CpuStat getCpuStat() {
		return cpuStat;
	}

	public void setCpuStat(CpuStat cpuStat) {
		this.cpuStat = cpuStat;
	}

	public TaskStat getMainTaskStat() {
		return mainTaskStat;
	}

	public void setMainTaskStat(TaskStat mainTaskStat) {
		this.mainTaskStat = mainTaskStat;
	}

	public ArrayList<TaskStat> getThreadTaskStat() {
		return threadTaskStat;
	}

	public void setThreadTaskStat(ArrayList<TaskStat> threadTaskStat) {
		this.threadTaskStat = threadTaskStat;
	}

	public void clear() {
		time = 0;
		cpuStat = null;
		mainTaskStat = null;
		cpuStatsList = null;
		threadTaskStat = null;
	}

	public CurrentStat clone() {
		return new CurrentStat(time, cpuStat, cpuStatsList, mainTaskStat, threadTaskStat);
	}

}
