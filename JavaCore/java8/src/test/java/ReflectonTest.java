import entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yujiaqi
 * @date 2019-08-29 13:05
 * @description
 */
public class ReflectonTest {


    private static  Employee employee;

    @Before
    public void init(){
        employee = new Employee(18);
        employee.setName("");
        employee.setSns(new HashSet<>());
    }
    @Test
    public void test01(){
        employee = new Employee(18);
        employee.setName("");
        employee.setSns(new HashSet<>());
        System.out.println(employee.toString());
        check(employee);
        System.out.println(employee.toString());
    }


    public void check(Object obj){
        Field[] fields=obj.getClass().getDeclaredFields();
        for(Field field:fields){
            try {
                if(field.getType().getTypeName().equals("java.util.Set")){
                    field.setAccessible(true);
                    Set<Object> sns = (Set<Object>)field.get(obj);
                    if(sns.size()==0){
                        field.set(obj,null);
                    }
                }
                if(field.getType().getTypeName().equals("java.lang.String")){
                    field.setAccessible(true);
                    String value = (String)field.get(obj);
                    if(value.length()==0){
                        field.set(obj,null);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
