package lock.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yujiaqi
 * @date 2019-07-02 21:54
 * @description
 */
public class CountDownLatchDemo implements Runnable{

    private static final CountDownLatch countDownLatch=new CountDownLatch(10);


    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatchDemo downLatchDemo=new CountDownLatchDemo();
        for(int i=0;i<10;i++){
            threadPool.submit(downLatchDemo);
        }
        //等待计数=0
        countDownLatch.await();
        System.out.println("exec.end");
        threadPool.shutdown();
    }
}
