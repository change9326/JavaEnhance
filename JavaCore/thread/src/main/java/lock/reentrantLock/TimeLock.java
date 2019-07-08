package lock.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yujiaqi
 * @date 2019-07-02 21:04
 * @description
 */
public class TimeLock implements Runnable{
    private static ReentrantLock lock=new ReentrantLock();
    private static final int LOCK_TIME_OUT=5;
    @Override
    public void run() {
        try {
            if(lock.tryLock(LOCK_TIME_OUT, TimeUnit.SECONDS)){
               Thread.sleep(6000L);
            }else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        new Thread(timeLock).start();
        new Thread(timeLock).start();
    }
}
