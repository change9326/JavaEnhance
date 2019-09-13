package com.learn.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yujiaqi
 * @date 2019-08-04 14:15
 * @description
 *
 * 任务队列：
 *   无界队列：
 *      如果当前已经创建的核心线程数小于线程池的核心线程数上限，则新建线程(核心线程)处理任务；
 *      如果当前已经创建的核心线程数等于核心线程数上限，则进入队列等待。由于这个队列没有最大值限制，
 *      即所有超过核心线程数的任务都将被添加到队列中，这也就导致了maximumPoolSize的设定失效，
 *      因为总线程数永远不会超过corePoolSize

 */
public class ThreadPoolTest {

    public void testCommon(ThreadPoolExecutor threadPoolExecutor) throws Exception {
        // 测试： 提交15个执行时间需要3秒的任务，看超过大小的2个，对应的处理情况
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行：" + n);
                        Thread.sleep(3000L);
                        System.err.println("执行结束:" + n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("任务提交成功 :" + i);
        }
        // 查看线程数量，查看队列等待数量
        Thread.sleep(500L);
        System.out.println("当前线程池线程数量为：" + threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待的数量为：" + threadPoolExecutor.getQueue().size());
        // 等待15秒，查看线程数量和队列数量（理论上，会被超出核心线程数量的线程自动销毁）
        Thread.sleep(15000L);
        System.out.println("当前线程池线程数量为：" + threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待的数量为：" + threadPoolExecutor.getQueue().size());
    }

    public void test01() throws Exception {
        /**
         * 线程池信息： 核心线程数量5，最大数量10，无界队列，超出核心线程数量的线程存活时间：5秒，默认拒绝策略AbortPolicy
         *
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                5, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        testCommon(threadPoolExecutor);
        // 预计结果：线程池线程数量为：5,超出数量的任务，其他的进入队列中等待被执行
    }
}
