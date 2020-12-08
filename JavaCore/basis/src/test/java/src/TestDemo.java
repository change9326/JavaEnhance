package src;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yujiaqi
 * @date 2019-09-07 13:49
 * @description
 */
public class TestDemo {

    private static final String name="银行帐号";

    private static int[] examineStatusArray= {1,2};

    @Test
    public void test01(){
        List<Integer> list1 = Arrays.asList(1, 2);

        List<Integer> list2 = Arrays.asList(3, 4);
        for(Integer list:list1){
            list2.remove("3");
        }
        System.out.println();
    }




    @Test
    public void test03(){
        Set<Integer> set1=new HashSet<>();
        set1.add(1);
        set1.add(3);
        Set<Long> set2=new HashSet<>();
        set2.add(1L);
        set2.add(2L);
        Set<Long> set3 = set1.stream().map(o -> Long.parseLong(o.toString())).collect(Collectors.toSet());
        set3.retainAll(set2);

        System.out.println(set3);

    }

    @Test
    public void test04(){
        List<String>  sns= new ArrayList<>();
        sns.add(null);
        System.out.println(sns.size());
        sns.remove(null);
        System.out.println(sns.size());

    }

    @Test
    public void test05(){

        //System.out.println("珈溪（北京）国际贸易有限公司菲洛嘉（北京）医疗美容医院连锁有限公司".length());


        System.out.printf(!Arrays.asList(0, 1).contains(2)+"");
    }

    public static String formatThousands(Object o) {
        String s= o=="0.00" ? "" : o.toString();
        DecimalFormat df= new DecimalFormat(",###,##0.00");
        return df.format(new BigDecimal(s));
    }

    @Test
    public void test(){
         MessageFormat KEY_FORMAT = new MessageFormat("ECM:Value:ZMXY:Source:{0}");
         System.out.println(KEY_FORMAT.format(new String[] { "testyjq" }));

    }

    @Test
    public void test1(){
        List<Map<String, Object>> maps=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String, Object> map=new HashMap<>();
            map.put("operation","同意");
            map.put("processorId",i);
            maps.add(map);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("operation","同意");
        map.put("processorId",100);
        maps.add(map);
        long count = maps.stream().map(o -> o.get("operation").toString()).filter(o -> o.equals("不同意")).count();
        System.out.println(count);
    }


     @Test
     public void test12(){
         System.out.println("B.业务稳定无需求".substring(2));
     }








}

class Student{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
