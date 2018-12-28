package com.sample.startup.gc.info;

import java.util.ArrayList;

public class ResultInfo {

	private String time;
	private String systemTotal;
	private String cpuCore;
	private String load;
	private String mainProcess;
	private ArrayList<String> threadProcess;

	public ResultInfo() {
	}

	public ResultInfo(String time, String systemTotal, String load, String mainProcess, ArrayList<String> threadProcess) {
		this.time = time;
		this.systemTotal = systemTotal;
		this.load = load;
		this.mainProcess = mainProcess;
		this.threadProcess = threadProcess;
	}

	public ResultInfo(String time, String systemTotal, String cpuCore, String load, String mainProcess, ArrayList<String> threadProcess) {
		this.time = time;
		this.systemTotal = systemTotal;
		this.cpuCore = cpuCore;
		this.load = load;
		this.mainProcess = mainProcess;
		this.threadProcess = threadProcess;
	}

	public String getCpuCore() {
		return cpuCore;
	}

	public void setCpuCore(String cpuCore) {
		this.cpuCore = cpuCore;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSystemTotal() {
		return systemTotal;
	}

	public void setSystemTotal(String systemTotal) {
		this.systemTotal = systemTotal;
	}

	public String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

	public String getMainProcess() {
		return mainProcess;
	}

	public void setMainProcess(String mainProcess) {
		this.mainProcess = mainProcess;
	}

	public ArrayList<String> getThreadProcess() {
		return threadProcess;
	}

	public void setThreadProcess(ArrayList<String> threadProcess) {
		this.threadProcess = threadProcess;
	}

}
