package src;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yujiaqi
 * @date 2019-09-07 13:49
 * @description
 */
public class TestDemo {

    private static int[] examineStatusArray= {1,2};

    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1, 2);
        System.out.println(list.contains(1));
    }
}
