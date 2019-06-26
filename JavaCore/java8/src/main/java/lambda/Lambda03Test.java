package lambda;

import entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yujiaqi
 * @date 2019-04-21 00:10
 * @description
 */
public class Lambda03Test {
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
     * 先按照年龄比较，年龄相同在按照姓名比较
     */
    @Test
    public void test01(){
        Collections.sort(employeeList,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for(Employee employee:employeeList){
            System.out.println(employee);
        }
    }
}
