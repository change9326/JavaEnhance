package stream;

import entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yujiaqi
 * @date 2019-04-21 10:48
 * @description
 *
 * 一.Stream 的是三个操作步骤
 *  1.创建stream
 *  2.中间操作
 *  3.终止操作
 *
 * 中间操作：
 *     筛选与切片
 *        filter:  接收lambda,从流中排除某些元素
 *        limit:   阶段流，使元素不超过给定数量
 *        skip(n): 跳过元素，返回一个扔掉前n个元素的的流。若流中元素不超过n个，则返回一个空流。与limit(n)互补。
 *        distinct:筛选，通过流生成元素的hashcode()和equals()去除重复元素。
 *     映射：
 *        map:    接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数被应用到每个元素上，并将其映射成一个新的元素。
 *        flatMap:接受一个函数作为参数，将流中每个值转换成另一个流，然后把所有流连接成一个流。
 *     排序：
 *     sorted():              自然排序（Comparable）
 *     sorted(Comparator com):定制排序 (Comparator)
 *
 *
 */
public class StreamApi01Test {


    private List<Employee> employeeList;

    @Before
    public void init(){
        employeeList = Arrays.asList(
                new Employee("1", 1, 9999.99),
                new Employee("2", 2, 999.99),
                new Employee("3", 38, 99999.99),
                new Employee("4", 48, 99999.99),
                new Employee("5", 58, 999999.99),
                new Employee("6", 58, 999999.99),
                new Employee("7", 58, 999999.99),
                new Employee("8", 58, 999999.99),
                new Employee("9", 18, 9999.99),
                new Employee("10", 18, 999.99),
                new Employee("11", 38, 99999.99),
                new Employee("12", 48, 99999.99),
                new Employee("13", 58, 999999.99),
                new Employee("14", 58, 999999.99),
                new Employee("15", 58, 999999.99),
                new Employee("16", 58, 999999.99)
        );
    }

    /**
     * 创建stream
     */
    @Test
    public void test01(){
        //1.通过Collection 系列集合提供的stream() 或paralleStream()
        List<String> list=new ArrayList<>();
        Stream<String> stream = list.stream();

        //2.通过Arrays 中的静态方法stream()获取数据流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        //3.通过stream 类中的静态方法of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //4.1迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);
        //4.2生成
        Stream.generate(()->Math.random())
                .limit(5)
                .forEach(System.out::println);

    }

    /**
      * 内部迭代：迭代操作由Stream Api 完成
     */
    @Test
    public void test02(){
        //中间操作，不会执行任何操作
        Stream<Employee> stream = employeeList.stream()
                .filter((employee) -> {
                    System.out.println("Stream Api 中间操作");
                    return employee.getAge() > 35;
                });
        //终止操作:一次性执行全部内容，即惰性求值
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    public void test03(){
        Iterator<Employee> iterator = employeeList.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test04(){
        employeeList.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * map映射
     */
    @Test
    public void test05(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee", "fff");
        list.stream()
                .map((str)->str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------");
        employeeList.stream()
                .map(Employee::getName)
                .distinct()
                .limit(2)
                .forEach(System.out::println);
        System.out.println("------------------");
        Stream<Stream<Character>> streamStream = list.stream()
                //{{a,a,a},{b,b,b}........}
                .map(StreamApi01Test::filterCharacter);
        // 使用map 需要迭代两次
        streamStream.forEach((stream)->
            stream.forEach(System.out::println)
        );
        System.out.println("------------------");
        Stream<Character> characterStream = list.stream()
                //{a,a,a,b,b,b........}
                .flatMap(StreamApi01Test::filterCharacter);
        characterStream.forEach(System.out::println);


    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list=new ArrayList<>();
        for(Character character:str.toCharArray()){
            list.add(character);
        }
        return list.stream();
    }

    @Test
    public void test06(){
        List<String> list = Arrays.asList("eee", "fff","aaa", "bbb", "ccc");
        //自然排序
        list.stream()
                .sorted()
                .forEach(System.out::println);
        //定制排序
        employeeList.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge()==e2.getAge()){
                        System.out.println();
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     * 分页
     */
    @Test
    public void test07(){
        Integer pageNum=2;
        Integer pageSize=5;
        List<Employee> list = employeeList.stream().
                skip(pageSize * (pageNum - 1)).
                limit(pageSize)
                .collect(Collectors.toList());
        System.out.println(list);
        //List<Employee> list2=null;
        //List<Employee> employees = Optional.ofNullable(list2).orElse(new ArrayList<>());
        System.out.println(list.size());
    }

    /**
     * 数据分区
     */
    @Test
    public void testPartitioningBy(){
        Map<Boolean, List<Employee>> collect = employeeList.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() >= 10));
        //{false=[Employee(name=1, age=1, salary=9999.99, status=null)], true=[Employee(name=3, age]}
        System.out.println(collect);
    }




}
