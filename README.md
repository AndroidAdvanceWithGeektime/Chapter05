# Chapter05
今天我们迎来了第二个课后作业，我们尝试模仿[ProcessCpuTracker.java](http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/com/android/internal/os/ProcessCpuTracker.java)拿到一段时间内各个线程的耗时占比

下面是一个结果的样例：
```
usage: CPU usage 5000ms(from 23:23:33.000 to 23:23:38.000):
System TOTAL: 2.1% user + 16% kernel + 9.2% iowait + 0.2% irq + 0.1% softirq + 72% idle
CPU Core: 8
Load Average: 8.74 / 7.74 / 7.36

Process:com.sample.app 
  50% 23468/com.sample.app(S): 11% user + 38% kernel faults:4965

Threads:
  43% 23493/singleThread(R): 6.5% user + 36% kernel faults：3094
  3.2% 23485/RenderThread(S): 2.1% user + 1% kernel faults：329
  0.3% 23468/.sample.app(S): 0.3% user + 0% kernel faults：6
  0.3% 23479/HeapTaskDaemon(S): 0.3% user + 0% kernel faults：982
  ...
```


实现提示
====
统计一段时间内的CPU使用情况

```
processCpuTracker.update();
dosomething();
processCpuTracker.update();
// 输出结果
processCpuTracker.printCurrentState(SystemClock.uptimeMillis());
```

其中数据可以从各个系统文件中获取：

### 系统CPU信息
系统CPU使用率可以通过 /proc/stat 文件获得，各个字段的含义可以参考文件[Linux环境下进程的CPU占用率](http://www.samirchen.com/linux-cpu-performance/)和[Linux文档](http://man7.org/linux/man-pages/man5/proc.5.html)

```
System TOTAL: 2.1% user + 16% kernel + 9.2% iowait + 0.2% irq + 0.1% softirq + 72% idle
```

### CPU核数
可以通过文件/proc/cpuinfo获得

```
CPU Core: 8
```

### 负载
系统通过/proc/loadavg获得

```
Load Average: 8.74 / 7.74 / 7.36
```

### 各线程负载
1. 从/proc/[pid]/stat可以获得进程CPU使用情况
2. 从/proc/[pid]/task/[tid]/stat可以获得进程下面各个线程的CPU使用情况

```
Threads:
  43% 23493/singleThread(R): 6.5% user + 36% kernel faults：3094
  3.2% 23485/RenderThread(S): 2.1% user + 1% kernel faults：329
  0.3% 23468/.sample.io.test(S): 0.3% user + 0% kernel faults：6
  0.3% 23479/HeapTaskDaemon(S): 0.3% user + 0% kernel faults：982
  ...
```

提交方法
====
整个提交方法如下：

1. 完善ProcessTrackerSample项目
2. 注明极客时间的账号 + 实现原理与心得体会
3. 发送pull request 到本repo

奖励
===
根据项目质量和提交pull request的时间，抽取部分同学送上经典书籍。

最终结果也会在极客时间和repo中公布，欢迎大家积极参与！