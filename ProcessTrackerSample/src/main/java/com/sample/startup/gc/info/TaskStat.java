package com.sample.startup.gc.info;

public class TaskStat {

	// 1~10
	private String pid;
	private String comm;
	private String state;
	private String ppid;
	private String pgid;
	private String session;
	private String tty_nr;
	private String tpgid;
	private String flags;
	private int minflt;

	// 11~20
	private int cminflt;
	private int majflt;
	private int cmajflt;
	private long utime;
	private long stime;
	private long cutime;
	private long cstime;
	private int priority;
	private int nice;
	private int num_threads;

	// 21~30
	private long itrealvalue;
	private long starttime;
	private long vsize;
	private long rss;
	private long rsslim;
	private String startcode;
	private String endcode;
	private String startstack;
	private String kstkesp;
	private String kstkeip;

	// 31~40
	private String signal;
	private String blocked;
	private String sigignore;
	private String sigcatch;
	private String wchan;
	private String nswap;
	private String cnswap;
	private String exit_signal;
	/**
	 * 该线程使用的是第几个CPU
	 */
	private int processor;
	private String rt_priority;


	// 41~50
	private String policy;
	private String delayacct_blkio_ticks;
	private String guest_time;
	private String cguest_time;
	private String start_data;
	private String end_data;
	private String start_brk;
	private String arg_start;
	private String arg_end;
	private String env_start;

	// 51、52
	private String env_end;
	private String exit_code;

	public TaskStat() {
	}

	public TaskStat(String pid, String comm, String state, String ppid, String pgid, String session,
	                String tty_nr, String tpgid, String flags, int minflt, int cminflt, int majflt,
	                int cmajflt, long utime, long stime, long cutime, long cstime, int priority,
	                int nice, int num_threads, long itrealvalue, long starttime, long vsize, long rss,
	                long rsslim, String startcode, String endcode, String startstack, String kstkesp,
	                String kstkeip, String signal, String blocked, String sigignore, String sigcatch,
	                String wchan, String nswap, String cnswap, String exit_signal, int processor,
	                String rt_priority, String policy, String delayacct_blkio_ticks, String guest_time,
	                String cguest_time, String start_data, String end_data, String start_brk,
	                String arg_start, String arg_end, String env_start, String env_end, String exit_code) {
		this.pid = pid;
		this.comm = comm;
		this.state = state;
		this.ppid = ppid;
		this.pgid = pgid;
		this.session = session;
		this.tty_nr = tty_nr;
		this.tpgid = tpgid;
		this.flags = flags;
		this.minflt = minflt;
		this.cminflt = cminflt;
		this.majflt = majflt;
		this.cmajflt = cmajflt;
		this.utime = utime;
		this.stime = stime;
		this.cutime = cutime;
		this.cstime = cstime;
		this.priority = priority;
		this.nice = nice;
		this.num_threads = num_threads;
		this.itrealvalue = itrealvalue;
		this.starttime = starttime;
		this.vsize = vsize;
		this.rss = rss;
		this.rsslim = rsslim;
		this.startcode = startcode;
		this.endcode = endcode;
		this.startstack = startstack;
		this.kstkesp = kstkesp;
		this.kstkeip = kstkeip;
		this.signal = signal;
		this.blocked = blocked;
		this.sigignore = sigignore;
		this.sigcatch = sigcatch;
		this.wchan = wchan;
		this.nswap = nswap;
		this.cnswap = cnswap;
		this.exit_signal = exit_signal;
		this.processor = processor;
		this.rt_priority = rt_priority;
		this.policy = policy;
		this.delayacct_blkio_ticks = delayacct_blkio_ticks;
		this.guest_time = guest_time;
		this.cguest_time = cguest_time;
		this.start_data = start_data;
		this.end_data = end_data;
		this.start_brk = start_brk;
		this.arg_start = arg_start;
		this.arg_end = arg_end;
		this.env_start = env_start;
		this.env_end = env_end;
		this.exit_code = exit_code;
	}

	public TaskStat(String pid, String comm, String state, String ppid, String pgid, String session,
	                String tty_nr, String tpgid, String flags, String minflt, String cminflt,
	                String majflt, String cmajflt, String utime, String stime, String cutime,
	                String cstime, String priority, String nice, String num_threads, String itrealvalue,
	                String starttime, String vsize, String rss, String rsslim, String startcode,
	                String endcode, String startstack, String kstkesp, String kstkeip,
	                String signal, String blocked, String sigignore, String sigcatch,
	                String wchan, String nswap, String cnswap, String exit_signal, String processor,
	                String rt_priority, String policy, String delayacct_blkio_ticks,
	                String guest_time, String cguest_time, String start_data, String end_data,
	                String start_brk, String arg_start, String arg_end, String env_start, String env_end, String exit_code) {
		this.pid = pid;
		this.comm = comm;
		this.state = state;
		this.ppid = ppid;
		this.pgid = pgid;
		this.session = session;
		this.tty_nr = tty_nr;
		this.tpgid = tpgid;
		this.flags = flags;
		this.minflt = Integer.parseInt(minflt);
		this.cminflt = Integer.parseInt(cminflt);
		this.majflt = Integer.parseInt(majflt);
		this.cmajflt = Integer.parseInt(cmajflt);
		this.utime = Long.parseLong(utime);
		this.stime = Long.parseLong(stime);
		this.cutime = Long.parseLong(cutime);
		this.cstime = Long.parseLong(cstime);
		this.priority = Integer.parseInt(priority);
		this.nice = Integer.parseInt(nice);
		this.num_threads = Integer.parseInt(num_threads);
		this.itrealvalue = Long.parseLong(itrealvalue);
		this.starttime = Long.parseLong(starttime);
		this.vsize = Long.parseLong(vsize);
		this.rss = Long.parseLong(rss);
		this.rsslim = Long.parseLong(rsslim);
		this.startcode = startcode;
		this.endcode = endcode;
		this.startstack = startstack;
		this.kstkesp = kstkesp;
		this.kstkeip = kstkeip;
		this.signal = signal;
		this.blocked = blocked;
		this.sigignore = sigignore;
		this.sigcatch = sigcatch;
		this.wchan = wchan;
		this.nswap = nswap;
		this.cnswap = cnswap;
		this.exit_signal = exit_signal;
		this.processor = Integer.parseInt(processor);
		this.rt_priority = rt_priority;
		this.policy = policy;
		this.delayacct_blkio_ticks = delayacct_blkio_ticks;
		this.guest_time = guest_time;
		this.cguest_time = cguest_time;
		this.start_data = start_data;
		this.end_data = end_data;
		this.start_brk = start_brk;
		this.arg_start = arg_start;
		this.arg_end = arg_end;
		this.env_start = env_start;
		this.env_end = env_end;
		this.exit_code = exit_code;
	}

	public TaskStat(String[] pam) {
		this(pam[0], pam[1], pam[2], pam[3], pam[4], pam[5], pam[6], pam[7], pam[8], pam[9],
				pam[10], pam[11], pam[12], pam[13], pam[14], pam[15], pam[16], pam[17], pam[18], pam[19],
				pam[20], pam[21], pam[22], pam[23], pam[24], pam[25], pam[26], pam[27], pam[28], pam[29],
				pam[30], pam[31], pam[32], pam[33], pam[34], pam[35], pam[36], pam[37], pam[38], pam[39],
				pam[40], pam[41], pam[42], pam[43], pam[44], pam[45], pam[46], pam[47], pam[48], pam[49],
				pam[50], pam[51]);
	}

	/**
	 * 该线程的 CPU 占用时间
	 */
	public long threadCPUTime() {
		return utime + stime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public String getPgid() {
		return pgid;
	}

	public void setPgid(String pgid) {
		this.pgid = pgid;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getTty_nr() {
		return tty_nr;
	}

	public void setTty_nr(String tty_nr) {
		this.tty_nr = tty_nr;
	}

	public String getTpgid() {
		return tpgid;
	}

	public void setTpgid(String tpgid) {
		this.tpgid = tpgid;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public int getMinflt() {
		return minflt;
	}

	public void setMinflt(int minflt) {
		this.minflt = minflt;
	}

	public int getCminflt() {
		return cminflt;
	}

	public void setCminflt(int cminflt) {
		this.cminflt = cminflt;
	}

	public int getMajflt() {
		return majflt;
	}

	public void setMajflt(int majflt) {
		this.majflt = majflt;
	}

	public int getCmajflt() {
		return cmajflt;
	}

	public void setCmajflt(int cmajflt) {
		this.cmajflt = cmajflt;
	}

	public long getUtime() {
		return utime;
	}

	public void setUtime(long utime) {
		this.utime = utime;
	}

	public long getStime() {
		return stime;
	}

	public void setStime(long stime) {
		this.stime = stime;
	}

	public long getCutime() {
		return cutime;
	}

	public void setCutime(long cutime) {
		this.cutime = cutime;
	}

	public long getCstime() {
		return cstime;
	}

	public void setCstime(long cstime) {
		this.cstime = cstime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getNice() {
		return nice;
	}

	public void setNice(int nice) {
		this.nice = nice;
	}

	public int getNum_threads() {
		return num_threads;
	}

	public void setNum_threads(int num_threads) {
		this.num_threads = num_threads;
	}

	public long getItrealvalue() {
		return itrealvalue;
	}

	public void setItrealvalue(long itrealvalue) {
		this.itrealvalue = itrealvalue;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getVsize() {
		return vsize;
	}

	public void setVsize(long vsize) {
		this.vsize = vsize;
	}

	public long getRss() {
		return rss;
	}

	public void setRss(long rss) {
		this.rss = rss;
	}

	public long getRsslim() {
		return rsslim;
	}

	public void setRsslim(long rsslim) {
		this.rsslim = rsslim;
	}

	public String getStartcode() {
		return startcode;
	}

	public void setStartcode(String startcode) {
		this.startcode = startcode;
	}

	public String getEndcode() {
		return endcode;
	}

	public void setEndcode(String endcode) {
		this.endcode = endcode;
	}

	public String getStartstack() {
		return startstack;
	}

	public void setStartstack(String startstack) {
		this.startstack = startstack;
	}

	public String getKstkesp() {
		return kstkesp;
	}

	public void setKstkesp(String kstkesp) {
		this.kstkesp = kstkesp;
	}

	public String getKstkeip() {
		return kstkeip;
	}

	public void setKstkeip(String kstkeip) {
		this.kstkeip = kstkeip;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public String getSigignore() {
		return sigignore;
	}

	public void setSigignore(String sigignore) {
		this.sigignore = sigignore;
	}

	public String getSigcatch() {
		return sigcatch;
	}

	public void setSigcatch(String sigcatch) {
		this.sigcatch = sigcatch;
	}

	public String getWchan() {
		return wchan;
	}

	public void setWchan(String wchan) {
		this.wchan = wchan;
	}

	public String getNswap() {
		return nswap;
	}

	public void setNswap(String nswap) {
		this.nswap = nswap;
	}

	public String getCnswap() {
		return cnswap;
	}

	public void setCnswap(String cnswap) {
		this.cnswap = cnswap;
	}

	public String getExit_signal() {
		return exit_signal;
	}

	public void setExit_signal(String exit_signal) {
		this.exit_signal = exit_signal;
	}

	public int getProcessor() {
		return processor;
	}

	public void setProcessor(int processor) {
		this.processor = processor;
	}

	public String getRt_priority() {
		return rt_priority;
	}

	public void setRt_priority(String rt_priority) {
		this.rt_priority = rt_priority;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getDelayacct_blkio_ticks() {
		return delayacct_blkio_ticks;
	}

	public void setDelayacct_blkio_ticks(String delayacct_blkio_ticks) {
		this.delayacct_blkio_ticks = delayacct_blkio_ticks;
	}

	public String getGuest_time() {
		return guest_time;
	}

	public void setGuest_time(String guest_time) {
		this.guest_time = guest_time;
	}

	public String getCguest_time() {
		return cguest_time;
	}

	public void setCguest_time(String cguest_time) {
		this.cguest_time = cguest_time;
	}

	public String getStart_data() {
		return start_data;
	}

	public void setStart_data(String start_data) {
		this.start_data = start_data;
	}

	public String getEnd_data() {
		return end_data;
	}

	public void setEnd_data(String end_data) {
		this.end_data = end_data;
	}

	public String getStart_brk() {
		return start_brk;
	}

	public void setStart_brk(String start_brk) {
		this.start_brk = start_brk;
	}

	public String getArg_start() {
		return arg_start;
	}

	public void setArg_start(String arg_start) {
		this.arg_start = arg_start;
	}

	public String getArg_end() {
		return arg_end;
	}

	public void setArg_end(String arg_end) {
		this.arg_end = arg_end;
	}

	public String getEnv_start() {
		return env_start;
	}

	public void setEnv_start(String env_start) {
		this.env_start = env_start;
	}

	public String getEnv_end() {
		return env_end;
	}

	public void setEnv_end(String env_end) {
		this.env_end = env_end;
	}

	public String getExit_code() {
		return exit_code;
	}

	public void setExit_code(String exit_code) {
		this.exit_code = exit_code;
	}

	@Override
	public String toString() {
		return "TaskStat{" +
				"pid='" + pid + '\'' +
				", comm='" + comm + '\'' +
				", state='" + state + '\'' +
				", ppid='" + ppid + '\'' +
				", pgid='" + pgid + '\'' +
				", session='" + session + '\'' +
				", tty_nr='" + tty_nr + '\'' +
				", tpgid='" + tpgid + '\'' +
				", flags='" + flags + '\'' +
				", minflt=" + minflt +
				", cminflt=" + cminflt +
				", majflt=" + majflt +
				", cmajflt=" + cmajflt +
				", utime=" + utime +
				", stime=" + stime +
				", cutime=" + cutime +
				", cstime=" + cstime +
				", priority=" + priority +
				", nice=" + nice +
				", num_threads=" + num_threads +
				", itrealvalue=" + itrealvalue +
				", starttime=" + starttime +
				", vsize=" + vsize +
				", rss=" + rss +
				", rsslim=" + rsslim +
				", startcode='" + startcode + '\'' +
				", endcode='" + endcode + '\'' +
				", startstack='" + startstack + '\'' +
				", kstkesp='" + kstkesp + '\'' +
				", kstkeip='" + kstkeip + '\'' +
				", signal='" + signal + '\'' +
				", blocked='" + blocked + '\'' +
				", sigignore='" + sigignore + '\'' +
				", sigcatch='" + sigcatch + '\'' +
				", wchan='" + wchan + '\'' +
				", nswap='" + nswap + '\'' +
				", cnswap='" + cnswap + '\'' +
				", exit_signal='" + exit_signal + '\'' +
				", processor='" + processor + '\'' +
				", rt_priority='" + rt_priority + '\'' +
				", policy='" + policy + '\'' +
				", delayacct_blkio_ticks='" + delayacct_blkio_ticks + '\'' +
				", guest_time='" + guest_time + '\'' +
				", cguest_time='" + cguest_time + '\'' +
				", start_data='" + start_data + '\'' +
				", end_data='" + end_data + '\'' +
				", start_brk='" + start_brk + '\'' +
				", arg_start='" + arg_start + '\'' +
				", arg_end='" + arg_end + '\'' +
				", env_start='" + env_start + '\'' +
				", env_end='" + env_end + '\'' +
				", exit_code='" + exit_code + '\'' +
				'}';
	}
}
