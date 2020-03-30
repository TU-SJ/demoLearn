package queue;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Netty延迟任务 {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) throws Exception {
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("RedPacketHashedWheelTimerWorker");
            return thread;
        };
        /**
         * 基于 netty 的时间轮算法 HashedWheelTimer 实现的延迟任务
         * @param tickDuration - 每tick一次的时间间隔
         * @param unit - tickDuration 的时间单位
         * @param ticksPerWheel - 时间轮中的槽数
         * @param leakDetection - 检查内存溢出
         */
        Timer timer = new HashedWheelTimer(factory, 1,
                TimeUnit.SECONDS, 100,true);
        System.out.println(String.format("开始任务时间:%s", LocalDateTime.now().format(F)));
        for(int i=1;i<10;i++){
            TimerTask timerTask = new Netty构造器(i);
            timer.newTimeout(timerTask, i, TimeUnit.SECONDS);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
