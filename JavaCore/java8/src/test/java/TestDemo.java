import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiaqi
 * @date 2019-05-21 13:53
 * @description
 */
public class TestDemo {

    @Test
    public void test01(){
        BigDecimal decimal1 = new BigDecimal(0);
        BigDecimal decimal2 = new BigDecimal(5000.01);
        System.out.println(decimal1.add(decimal2));
    }
    @Test
    public void test02(){
       List<String> sns=new ArrayList<>();
       sns.add("1");
       sns.add("2");
       System.out.println(sns.contains("1"));

    }

    @Test
    public void test03(){
        System.out.println(new BigInteger("8000").compareTo(new BigInteger("6000")));
    }


}
