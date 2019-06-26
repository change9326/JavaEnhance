package lambda;

import entity.Employee;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * @author yujiaqi
 * @date 2019-04-20 21:56
 * @description
 */
public class Lambda01Test {
    private List<Employee> employeeList;

    @Before
    public void init(){
        employeeList = Arrays.asList(
                new Employee("张三", 18, 999.99),
                new Employee("李四", 28, 9999.99),
                new Employee("王五", 38, 99999.99),
                new Employee("赵六", 48, 99999.99),
                new Employee("老七", 58, 999999.99)
        );
    }

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test01(){
        Comparator<Integer> comparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda 表达式
     */
    @Test
    public void test02(){
        Comparator<Integer> comparator= (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda 表达式实现
     */
    @Test
    public void test03(){
        List<Employee> employees = filterEmployee(employeeList, (employee -> employee.getSalary() >= 5000));
        employees.forEach(employee -> System.out.println(employee.toString()));
    }

    /**
     * Stream API
     */
    @Test
    public void test04(){
       employeeList.stream()
                .filter((employee -> employee.getSalary() >= 5000))
                .limit(2)
                .forEach(System.out::println);
       System.out.println("---------------------");
       employeeList.stream()
               .map(Employee::getName)
               .forEach(System.out::println);

    }

    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
        List<Employee> emps=new ArrayList<>();
        for (Employee employee:list) {
            if(mp.action(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }


}
