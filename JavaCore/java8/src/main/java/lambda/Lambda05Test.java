package lambda;

import entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author yujiaqi
 * @date 2019-04-21 09:13
 * @description
 *
 * 一.方法应用：若Lambda 体中的内容有方法已经实现了，我们可以使用方法引用；
 *         （可以理解为方法应用是Lambda 表达式的另外一种表现形式）
 * 三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 * 注意：
 * 1.Lambda 体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型一致！
 * 2.若Lambda 参数列表的中的第一个参数是实例方法的调用者，而第二个方法是实例方法的参数时，可以使用ClassName::method
 *
 * 二.构造器引用
 *
 * 格式：
 *
 * ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三.数组引用
 *
 * Type[]::new
 *
 *
 */
public class Lambda05Test {

    @Test
    public void test06(){
        Function<Integer,String[]> func=(x)->new String[x];
        String[] strings = func.apply(10);
        System.out.println(strings.length);

        Function<Integer,String[]> func2=String[]::new;
        String[] strings1 = func2.apply(10);
        System.out.println(strings1.length);
    }

    /**
     * 构造器引用
     */
    @Test
    public void test05(){
        Supplier<Employee> supplier=()->new Employee();
        //构造器引用方式(无参构造函数)
        Supplier<Employee> supplier1=Employee::new;
        Employee employee = supplier1.get();

        //
        Function<Integer,Employee> function=(x)->new Employee(x);
        Employee employee1 = function.apply(200);
        System.out.println(employee1);
    }


    /**
     * 类::实例方法名
     */
    public void test04(){
        BiPredicate<String,String> biPredicate1=(x,y)->x.equals(y);

        BiPredicate<String,String> biPredicate2=String::equals;
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test03(){
        Comparator<Integer> comparator1=(x,y)->Integer.compare(x,y);

        Comparator<Integer> comparator2=Integer::compare;
    }

    /**
     * 对象::实例方法名
     */

    @Test
    public void test01(){
        PrintStream printStream = System.out;
        Consumer<String> consumer=(x)->printStream.println(x);
        consumer.accept("12312");
        System.out.println("-----------");
        Consumer<String> consumer1=printStream::println;
        consumer1.accept("werw");
    }

    @Test
    public void test02(){
        Employee employee = new Employee();
        Supplier<String> sup = () -> employee.getName();
        String name = sup.get();
        System.out.println(name);

        Supplier<Integer> sup1 = employee::getAge;
        Integer age = sup1.get();
        System.out.println(age);

    }
}
