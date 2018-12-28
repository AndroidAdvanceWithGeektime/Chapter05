package com.sample.startup.gc.info;

import java.text.DecimalFormat;

public class CpuStat {

	/**
	 * 从系统启动开始累积到当前时刻，处于用户态的运行时间，不包含 nice 值为负的进程。
	 */
	private long user;
	/**
	 * 从系统启动开始累积到当前时刻，nice 值为负的进程所占用的 CPU 时间。
	 */
	private long nice;
	/**
	 * 从系统启动开始累积到当前时刻，处于核心态的运行时间。
	 */
	private long system;
	/**
	 * 从系统启动开始累积到当前时刻，除 IO 等待时间以外的其他等待时间。
	 */
	private long idle;
	/**
	 * 从系统启动开始累积到当前时刻，IO 等待时间。
	 */
	private long iowait;
	/**
	 * 从系统启动开始累积到当前时刻，硬中断时间（硬件中断）。
	 */
	private long irq;
	/**
	 * 从系统启动开始累积到当前时刻，软中断时间（软件中断）。
	 */
	private long softirq;
	/**
	 * 失窃时间，当在虚拟化环境中运行时在其他操作系统中花费的时间
	 */
	private long steal;
	/**
	 * Time spent running a virtual CPU for guest operating systems under the control of the Linux kernel.
	 * 在Linux内核的控制下运行guest操作系统的虚拟CPU所花费的时间。
	 */
	private long guest;
	/**
	 * 运行一个好的guest(Linux内核控制下的guest操作系统的虚拟CPU)所花费的时间。
	 */
	private long guest_nice;

	public CpuStat(long user, long nice, long system, long idle, long iowait, long irq, long softirq, long steal, long guest, long guest_nice) {
		this.user = user;
		this.nice = nice;
		this.system = system;
		this.idle = idle;
		this.iowait = iowait;
		this.irq = irq;
		this.softirq = softirq;
		this.steal = steal;
		this.guest = guest;
		this.guest_nice = guest_nice;
	}

	public CpuStat(String user, String nice, String system, String idle, String iowait, String irq, String softirq, String steal, String guest, String guest_nice) {
		try {
			this.user = Long.parseLong(user);
			this.nice = Long.parseLong(nice);
			this.system = Long.parseLong(system);
			this.idle = Long.parseLong(idle);
			this.iowait = Long.parseLong(iowait);
			this.irq = Long.parseLong(irq);
			this.softirq = Long.parseLong(softirq);
			this.steal = Long.parseLong(steal);
			this.guest = Long.parseLong(guest);
			this.guest_nice = Long.parseLong(guest_nice);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("转换异常：" + e.getMessage());
		}
	}

	public CpuStat(String[] cpuinfo) {
		this(cpuinfo[0], cpuinfo[1], cpuinfo[2], cpuinfo[3], cpuinfo[4], cpuinfo[5], cpuinfo[6], cpuinfo[7], cpuinfo[8], cpuinfo[9]);
//		if (cpuinfo.length != 10) {
//			throw new IllegalArgumentException("cpuinfo.length != 10, cpuinfo.length = " + cpuinfo.length);
//		}
	}

	public CpuStat() {
	}

	/**
	 * 获取时间差中的两个CpuStat之间的差
	 */
	public static CpuStat gapCpuStat(CpuStat newStat, CpuStat oldStat) {
		CpuStat result = new CpuStat();

		long gap_user = newStat.getUser() - oldStat.getUser();
		long gap_nice = newStat.getNice() - oldStat.getNice();
		long gap_system = newStat.getSystem() - oldStat.getSystem();
		long gap_idle = newStat.getIdle() - oldStat.getIdle();
		long gap_iowait = newStat.getIowait() - oldStat.getIowait();
		long gap_irq = newStat.getIrq() - oldStat.getIrq();
		long gap_softirq = newStat.getSoftirq() - oldStat.getSoftirq();
		long gap_steal = newStat.getSteal() - oldStat.getSteal();
		long gap_guest = newStat.getGuest() - oldStat.getGuest();
		long gap_guest_nice = newStat.getGuest_nice() - oldStat.getGuest_nice();

		result.setUser(gap_user);
		result.setNice(gap_nice);
		result.setSystem(gap_system);
		result.setIdle(gap_idle);
		result.setIowait(gap_iowait);
		result.setIrq(gap_irq);
		result.setSoftirq(gap_softirq);
		result.setSteal(gap_steal);
		result.setGuest(gap_guest);
		result.setGuest_nice(gap_guest_nice);

		return result;
	}

	public String getUserRate() {
		long totalTime = totalCPUTime();
		double v = (user + nice) * 1.0 / totalTime;
		return adouble(v);
	}

	public String getSystemRate() {
		long totalTime = totalCPUTime();
		double v = system * 1.0 / totalTime;
		return adouble(v);
	}

	/**
	 * CPU使用率
	 * 该方法，当调用了gapCpuStat方法后，再用获得的新的CpuStat对象，使用此方法
	 */
	public String getCpuRate() {
		long totalCPUTime = totalCPUTime();
		double cpuRate = (totalCPUTime - idle) * 1.0 / totalCPUTime;
		return adouble(cpuRate);
	}

	/**
	 * 获取CPU信息
	 */
	public String getSystemInfo(long mJiffyMillis) {
		long totalTime = totalCPUTime();

		// 总user = user + nice，正常的计算方式是总user = (user + nice) * mJiffyMillis，由于这里算百分比，所以省去*mJiffyMillis
		double userRate = (user + nice) * 1.0 / totalTime;
		double iowaitRate = iowait * 1.0 / totalTime;
		double irqRate = irq * 1.0 / totalTime;
		double softirqRate = softirq * 1.0 / totalTime;
		double idleRate = idle * 1.0 / totalTime;

		double systemRate = system * 1.0 / totalTime;

		return "System TOTAL: " + adouble(userRate) + "% user + " + adouble(systemRate) +
				"% kernel + " + adouble(iowaitRate) + "% iowait + " + adouble(irqRate) +
				"% irq + " + adouble(softirqRate) + "% softirq + " + adouble(idleRate) + "% idle";

	}

	private String adouble(double d) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(d);
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getNice() {
		return nice;
	}

	public void setNice(long nice) {
		this.nice = nice;
	}

	public long getSystem() {
		return system;
	}

	public void setSystem(long system) {
		this.system = system;
	}

	public long getIdle() {
		return idle;
	}

	public void setIdle(long idle) {
		this.idle = idle;
	}

	public long getIowait() {
		return iowait;
	}

	public void setIowait(long iowait) {
		this.iowait = iowait;
	}

	public long getIrq() {
		return irq;
	}

	public void setIrq(long irq) {
		this.irq = irq;
	}

	public long getSoftirq() {
		return softirq;
	}

	public void setSoftirq(long softirq) {
		this.softirq = softirq;
	}

	public long getSteal() {
		return steal;
	}

	public void setSteal(long steal) {
		this.steal = steal;
	}

	public long getGuest() {
		return guest;
	}

	public void setGuest(long guest) {
		this.guest = guest;
	}

	public long getGuest_nice() {
		return guest_nice;
	}

	public void setGuest_nice(long guest_nice) {
		this.guest_nice = guest_nice;
	}

	/**
	 * 获取CPU运行总时间
	 */
	public long totalCPUTime() {
		return user + nice + system + idle + iowait + irq + softirq + steal + guest + guest_nice;
	}

	@Override
	public String toString() {
		return "CpuStat{" +
				"user=" + user +
				", nice=" + nice +
				", system=" + system +
				", idle=" + idle +
				", iowait=" + iowait +
				", irq=" + irq +
				", softirq=" + softirq +
				", steal=" + steal +
				", guest=" + guest +
				", guest_nice=" + guest_nice +
				'}';
	}

}
