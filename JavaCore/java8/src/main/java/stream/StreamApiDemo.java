package stream;

import entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * @author yujiaqi
 * @date 2019-04-21 17:18
 * @description
 * StreamApi 练习题
 */
public class StreamApiDemo {

    private List<Employee> employeeList;

    @Before
    public void init(){
        employeeList = Arrays.asList(
                new Employee("李四", 18, 9999.99),
                new Employee("张三", 18, 999.99),
                new Employee("王五", 38, 99999.99),
                new Employee("王五", 39, 99999.99)
        );
    }
    /**
     * 1.给定一个数字列表，返回有每一个数字平方构成的列表
     */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream()
                .map((x) -> x * x)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 2.使用map和reduce 统计Employee数量
     */
    @Test
    public void test02(){
        Optional<Integer> sum = employeeList.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }

    @Test
    public void test03(){
    }
}
