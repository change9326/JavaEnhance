package src;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yujiaqi
 * @date 2019-08-08 20:48
 * @description
 */
public class ListTest {

    @Test
    public void test01(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> sns=new HashSet<>();
        Set<Integer> sns1=new HashSet<>();
        sns.add(66);
        sns.addAll(sns1);
        //sns.addAll(list.subList(0,3));
        System.out.println("---");
    }
}
