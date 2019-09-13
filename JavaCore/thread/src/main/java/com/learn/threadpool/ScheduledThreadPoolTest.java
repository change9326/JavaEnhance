package com.learn.threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author yujiaqi
 * @date 2019-08-04 10:09
 * @description:
 * ScheduledThreadPool:
 *   计划任务，在指定的时间对任务进行调度
 *
 */
public class ScheduledThreadPoolTest {

    @Test
    public void test01() throws IOException {
        /**
         * 定时执行线程池信息:scheduledExecutor.schedule(),3s 后执行，一次性任务
         *
         * 继承ThreadPoolExecutor；super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue())
         *
         * 和Executors.newScheduledThreadPool()一样的
         */

        ScheduledExecutorService threadPoolExecutor =  new ScheduledThreadPoolExecutor(5);
        threadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，现在时间：" +  System.currentTimeMillis());
                System.exit(0);
            }
        },3000, TimeUnit.MILLISECONDS);
        System.out.println(
                "定时任务，提交成功，时间是：" + System.currentTimeMillis() + ", 当前线程池中线程数量：" + ((ScheduledThreadPoolExecutor) threadPoolExecutor).getPoolSize());
        System.in.read();
    }


    public void test02(){
        /**
         * 周期性执行某一个任务，线程池提供了两种调度方式:
         *   1.threadPoolExecutor.scheduleAtFixedRate()
         *
         *   2.threadPoolExecutor.scheduleWithFixedDelay()
         *
         *
         *   测试场景：提交的任务需要3秒才能执行完毕。看两种不同调度方式的区别
         */
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);


        /**
         * 效果1： 提交后，2秒后开始第一次执行，之后每间隔1秒，固定执行一次(如果发现上次执行还未完毕，则等待完毕，完毕后立刻执行)。
         * 也就是说这个代码中是，3秒钟执行一次（计算方式：每次执行三秒，间隔时间1秒，执行结束后马上开始下一次执行，无需等待）
         */
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务-1 被执行，现在时间：" + System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.MILLISECONDS);
        /**
         * 效果2：提交后，2秒后开始第一次执行，之后每间隔1秒，固定执行一次(如果发现上次执行还未完毕，则等待完毕，等上一次执行完毕后再开始计时，等待1秒)。
         * 也就是说这个代码钟的效果看到的是：4秒执行一次。 （计算方式：每次执行3秒，间隔时间1秒，执行完以后再等待1秒，所以是 3+1）
         */
        threadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务-2 被执行，现在时间：" + System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        new ScheduledThreadPoolTest().test02();
    }
}
