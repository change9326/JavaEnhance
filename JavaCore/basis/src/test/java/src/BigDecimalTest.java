package src;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author yujiaqi
 * @date 2019-07-11 16:52
 * @description:https://juejin.im/post/5c2f77f3518825260a7dc172
 */

public class BigDecimalTest {

    @Test
    public void test01(){
        //不允许使用,精度不能保证
        BigDecimal decimal1 = new BigDecimal(0.1);
        System.out.println(decimal1);

        //常用,推荐使用
        BigDecimal decimal2 = new BigDecimal("0.111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println(decimal2);

        //常用,推荐使用
        BigDecimal decimal3 = BigDecimal.valueOf(0.1);
        System.out.println(decimal3);

        System.out.println(BigDecimal.ZERO);
    }

    @Test
    public void test02(){
        BigDecimal bd1 = new BigDecimal("0.09");
        BigDecimal bd2 = new BigDecimal("0.01");
        System.out.println("加：" + bd1.add(bd2));

        BigDecimal bd3 = new BigDecimal("1.0");
        BigDecimal bd4 = new BigDecimal("0.32");
        System.out.println("减：" + bd3.subtract(bd4));

        BigDecimal bd5 = new BigDecimal("1.026");
        BigDecimal bd6 = new BigDecimal("100");
        System.out.println("乘：" + bd5.multiply(bd6));

        BigDecimal bd7 = new BigDecimal("1.502");
        BigDecimal bd8 = new BigDecimal("100");
        System.out.println("除：" + bd7.divide(bd8));
    }

    @Test
    public void test03(){
        /**
         * BigDecimal.ROUND_HALF_UP 四舍五入模式
         */
        BigDecimal b1 = new BigDecimal("1.1449");
        System.out.println(b1.setScale(2, BigDecimal.ROUND_HALF_UP));//1.14
        System.out.println(b1.setScale(3, BigDecimal.ROUND_HALF_UP));//1.145
    }
    @Test
    public void test04(){
        /**
         * BigDecimal.ROUND_HALF_DOWN 五舍六入模式
         * 如果舍去部分大于 0.5 则为进一，如果是小于 0.5 则会舍去
         */
        BigDecimal b1 = new BigDecimal("1.1456");
        System.out.println(b1.setScale(1, BigDecimal.ROUND_HALF_DOWN));//1.1
        System.out.println(b1.setScale(2, BigDecimal.ROUND_HALF_DOWN));//1.15
        //上例中舍去部分.56 大于.5 所以会进一

        BigDecimal b2 = new BigDecimal("1.145");
        System.out.println(b2.setScale(1, BigDecimal.ROUND_HALF_DOWN));//1.1
        System.out.println(b2.setScale(2, BigDecimal.ROUND_HALF_DOWN));//1.14
        //上例舍去部分 .5 不大于.5 所以会舍去

    }
    @Test
    public void test05(){
        /**
         BigDecimal.ROUND_HALF_EVEN 银行家舍入法 如果舍弃部分左边的数字为奇数，则舍入行为与 ROUND_HALF_UP 相同;
         如果为偶数，则舍入行为与 ROUND_HALF_DOWN 相同。
         */
        BigDecimal b1 = new BigDecimal("1.25");
        System.out.println(b1.setScale(1, BigDecimal.ROUND_HALF_EVEN));//1.2
        b1 = new BigDecimal("1.26");
        System.out.println(b1.setScale(1, BigDecimal.ROUND_HALF_EVEN));//1.3
        b1 = new BigDecimal("1.35");
        System.out.println(b1.setScale(1, BigDecimal.ROUND_HALF_EVEN));//1.4
        b1 = new BigDecimal("1.36");
        System.out.println(b1.setScale(1, BigDecimal.ROUND_HALF_EVEN));//1.4

    }

    @Test
    public void test06(){
        BigDecimal b1 = new BigDecimal("100");
        b1.add(new BigDecimal("200"));
        System.out.println(b1);
    }
    @Test
    public void test07(){
        //System.out.println(BigDecimal.ZERO);
        //boolean b = new BigDecimal("0.00").compareTo(BigDecimal.ZERO) == 0;
        BigDecimal a = new BigDecimal("5000000");
        BigDecimal b = new BigDecimal("50000");
        System.out.println(a.compareTo(b));

    }

    @Test
    public void test08(){
        BigDecimal bigDecimal = new BigDecimal("111231.5585990").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.printf(bigDecimal.toString());
    }

    @Test
    public void test09(){
        String s="<<<<<<<<<<<<<>>>>>>>>>>>>>>";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(s);
        System.out.printf(stringBuilder.toString());
    }

    @Test
    public void test10(){
        String company="深圳市连胜八网络科技有限公司_MTM1NQ==";
        getEncrypt(company);
        System.out.printf( getEncrypt(company));

    }
    public static String getEncrypt(String str){

            int indexOf = str.lastIndexOf("_");
            if(indexOf!=-1){
                return str.substring(indexOf+1);
            }

            return "";

    }
}


