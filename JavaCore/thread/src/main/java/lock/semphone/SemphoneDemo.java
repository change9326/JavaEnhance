package lock.semphone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author yujiaqi
 * @date 2019-07-02 21:39
 * @description
 */
public class SemphoneDemo implements Runnable{
    /**
     * 指定信号量的准入数为5，即申请5个许可
     */
    final Semaphore semaphore=new Semaphore(5);

    @Override
    public void run() {
        try {
            //获得准入的许可
            //同时允许有5个线程进入
            semaphore.acquire();
            Thread.sleep(2000L);
            System.out.println(Thread.currentThread().getId()+":done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        final SemphoneDemo semphoneDemo = new SemphoneDemo();
        for(int i=0;i<20;i++){
            threadPool.submit(semphoneDemo);
        }
        threadPool.shutdown();
    }
}
