package src;

import java.util.concurrent.*;

/**
 * @author yujiaqi
 * @date 2019-08-08 21:19
 * @description
 */
public class ThreadPoolTest {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(6);
    public static void main(String[] args) {


        Future<String> future1 = threadPool.submit(() -> {
            Thread.sleep(2000L);
            System.out.println("future1 end");
            return "success";
        });
        Future<String> future2 = threadPool.submit(() -> {
            Thread.sleep(3000L);
            System.out.println("future2 end");
            try {
                int i=1/0;
            }catch (Exception e){

                e.printStackTrace();
                return "error";
            }
            return "success";
        });
        Future<String> future3 = threadPool.submit(() -> {
            Thread.sleep(1000L);
            System.out.println("future3 end");
            return "success";
        });
        while (true){
            try {
                if(future1.isDone()&&future2.isDone()&&future3.isDone()
                &&future2.get().equals("success")){
                    System.out.println("all future done");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(" future error");
            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println(" future error");
            }
        }
        threadPool.shutdown();
        System.out.println("last end");
    }
}
