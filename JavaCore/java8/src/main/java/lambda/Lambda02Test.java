package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author yujiaqi
 * @date 2019-04-20 23:13
 * @description
 * 一.Lambda 表达式基础语法：Java8 中引入了一个新的操作符"->",该操作符成为箭头操作符或Lambda 操作符；
 *                     箭头操作符将Lambda 表达式拆分成左右两部分。
 *                     左侧：Lambda 表达式的参数列表；
 *                     右侧：Lambda 表达式需要执行的的功能，即Lambda 体
 *
 * 语法格式一：无参数，无返回值
 *         ()-> System.out.println("-----------");
 * 语法格式二：有一个参数(括号可以省略不写)，并且无返回值
 *         (x)->System.out.print(x);
 * 语法格式三：有两个以上的参数，有返回值，并且Lambda 体中有多条语句；
 *          (x,y)->{};
 * 语法格式四：若Lambda 体中只有一条语句，return和大括号都可以省略不写
 *          Comparator<Integer> comparator=(x,y)->Integer.compare(x,y)
 *
 * 语法格式五：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即"类型推断"；
 *          (Integer x,Integer y)->Integer.compare(x,y)
 *
 * 二.Lambda 表达式需要"函数式接口"的支持
 * "函数式接口"：接口中只有一个抽象方法；可以使用@FunctionalInterface 注解修饰，可以检查是否是函数式接口
 *
 */
public class Lambda02Test {

    @Test
    public void test01(){
        //jdk1.7 以前必须加final;1.8 默认final
        int num=0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!"+num);
            }
        };
        r.run();
        System.out.println("-----------");
        Runnable r1 = ()-> System.out.println("Hello Lambda!"+num);
        r1.run();
    }
    @Test
    public void test02(){
        //此处lambda 表达式就是对Consumer 接口中accept 方法的实现
        Consumer<String> consumer=(x)-> System.out.println(x);
        consumer.accept("此处lambda 表达式就是对Consumer 接口中accept 方法的实现");
    }
    @Test
    public void test03(){
        Comparator<Integer> comparator=(x,y)->{
            System.out.println("函数式编程");
            return Integer.compare(x,y);
        };
    }
    @Test
    public void test04(){
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);
    }

    @Test
    public void test05(){
        Integer res = operaion(100, x -> x * x);
        System.out.println(res);
        System.out.println("--------");
        System.out.println(operaion(100,x->x+200));
    }

    public Integer operaion(Integer num,MyFunc mf){
        return mf.getValue(num);
    }



}
