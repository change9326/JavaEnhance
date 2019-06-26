package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author yujiaqi
 * @date 2019-04-21 00:29
 * @description
 * Java8 内置四大核心函数式接口
 *
 * Consumer<T>:消费型接口
 *            void accept(T t);
 *
 * Supplier<T>:供给型接口
 *            T get();
 *
 * Function<T,R>:函数型接口
 *              R apply(T t);
 *
 * Predicate<T>:断言型接口
 *             boolean test(T t);
 *
 */
public class Lambda04Test {



    /**
     * 一.Consumer<T>:消费型接口
     */
    @Test
    public void test01(){
        happy(1000,(m)-> System.out.println("Happy 消费："+m+"元"));
    }
    public void happy(double money, Consumer<Double> con){
         con.accept(money);
    }

    /**
     * 二.Supplier<T>供给型接口
     */
    @Test
    public void test02(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    /**
     * 产生指定个数的整数，并放入集合中
     * @param num
     * @param sup
     * @return
     */
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            list.add(sup.get());
        }
        return list;
    }

    /**
     * 三. Function<T,R>:函数型接口
     */
    @Test
    public void test03(){
        String res = strHandler("abcasd", (str) -> str.toUpperCase());
        System.out.println(res);
    }
    public String strHandler(String str, Function<String,String> func){
        return func.apply(str);
    }

    /**
     * 四.Predicate<T>:断言型接口
     */
    @Test
    public void test04(){
        List<String> list = Arrays.asList("Lambda", "ok", "www", "Predicate<T>:断言型接口");
        List<String> strings = filterStr(list, (s) -> s.length() >= 3);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> stringList=new ArrayList<>();
        for (String s : list) {
            if(pre.test(s)){
                stringList.add(s);
            }
        }
        return stringList;
    }
}
