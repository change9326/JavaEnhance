package outOfMemoryError;

/**
 * @author yujiaqi
 * @date 2020-04-29 11:28
 * @description
 */
public class JavaVMStackSOF {

    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom=new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Exception e){
            System.out.println(oom.stackLength);
            throw e;
        }
    }
}
