package stream;

import entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yujiaqi
 * @date 2019-04-21 14:59
 * @description
 *
 * 终止操作：
 *       查找与匹配：
 *               allMatch:检查是否匹配所有元素
 *               anyMatch:检查是否至少匹配一个元素
 *               noneMatch:检查是否没有匹配的元素
 *               findFirst:返回第一个元素
 *               findAny:返回当前流中任意元素
 *               count:返回流中元素总个数
 *               max:返回流中最大值
 *               min:返回流中最小值
 *       归约：
 *          reduce(T identity,BinaryOperator)/reduce(BinaryOperator)：可以将流中元素反复结合起来，得到一个值。
 *       收集：
 *          collect:将流转换成其他形式。接收一个Collector 接口的实现，用于给stream 中元素做汇总的方法。
 *                 总数:
 *                 平均值:
 *                 总和:
 *                 最大值:
 *                 最小值:
 *                 分组：
 *                 多级分组：
 *                 分区：
 *                 拼接：join()
 */
public class StreamApi02Test {
    private List<Employee> employeeList;

    @Before
    public void init(){
        employeeList = Arrays.asList(
                new Employee("李四", 18, 9999.99, Employee.Status.FREE),
                new Employee("张三", 18, 999.99, Employee.Status.BUSY),
                new Employee("王五", 38, 99999.99, Employee.Status.VACATION),
                new Employee("赵六", 48, 99999.99, Employee.Status.FREE),
                new Employee("老七", 58, 999999.99, Employee.Status.BUSY),
                new Employee("老七", 58, 9999989.1299, Employee.Status.BUSY));
    }

    @Test
    public void test(){
        String s = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","前缀=","=后缀"));
        System.out.println(s);
    }
    @Test
    public void test06(){
        //分组
//        Map<Employee.Status, List<Employee>> collect = employeeList.stream()
//                .collect(Collectors.groupingBy(Employee::getStatus));
//        System.out.println(collect);
        //多级分组[先按照状态分组，在按照年龄分组]
        Map<Employee.Status, Map<String, List<Employee>>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
        System.out.println();
        //分区[true/false]
//        Map<Boolean, List<Employee>> map1 = employeeList.stream()
//                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
//        System.out.println(map1);
//        System.out.println("-----------------");
//        DoubleSummaryStatistics statistics = employeeList.stream()
//                .collect(Collectors.summarizingDouble(Employee::getSalary));
//        System.out.println(statistics.getMax());
//        System.out.println(statistics.getAverage());
//        System.out.println(statistics.getSum());
//        System.out.println("-----------------");

    }
    @Test
    public void test05(){
        //总数
        Long count = employeeList.stream()
                .collect(Collectors.counting());
        System.out.println("总数："+count);
        System.out.println("-----------------");
        //平均值
        Double averaging = employeeList.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均值："+averaging);
        System.out.println("-----------------");
        //总和
        Double sum = employeeList.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("总和："+sum);
        System.out.println("-----------------");
        //最大值
        Optional<Employee> max = employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
        System.out.println("最大值:"+max.get());
        System.out.println("-----------------");
        //最小值
        Optional<Double> min = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println("最小值"+min.get());
    }
    @Test
    public void test04(){
        List<String> list = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------------");
        Set<String> set = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-------------------");
        HashSet<String> hashSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }
    @Test
    public void test03(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("----------------");

        Optional<Double> op = employeeList.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }
    @Test
    public void test01(){
        boolean b1 = employeeList.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        System.out.println("------------");
        boolean b2 = employeeList.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        System.out.println("------------");
        boolean b3 = employeeList.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        System.out.println("------------");
        Optional<Employee> op = employeeList.stream()
                .filter((e) -> e.getName().equals("李四"))
                .findFirst();
        System.out.println(op.get());
        System.out.println("------------");
        Optional<Employee> op1 = employeeList.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.BUSY))
                .findAny();
        System.out.println(op1.get());
    }

    @Test
    public void test02(){
        long count = employeeList.stream()
                .count();
        System.out.println(count);

        Optional<Employee> op1 = employeeList.stream()
                //(e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())
                .max(Comparator.comparing(Employee::getSalary));
        System.out.println(op1.get());

        Optional<Double> op2 = employeeList.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op2.get());
    }
}
