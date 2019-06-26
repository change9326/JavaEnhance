package optional;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author yujiaqi
 * @date 2019-04-22 08:29
 * @description
 *
 * optional 常用方法：
 *    Optional.of(T t) : 创建一个 Optional 实例
 *    Optional.empty() : 创建一个空的 Optional 实例
 *    Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例 isPresent() : 判断是否包含值
 *    orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
 *    orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 *    map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 *    flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 *
 *
 *
 */
public class OptionalTest {
    @Test
    public void test01(){

        Map<String,Object> m=new HashMap<>();
        m.put("key1",0.00);
        BigDecimal key1 = Optional.ofNullable((BigDecimal) m.get("key1")).orElse(BigDecimal.ZERO);
        System.out.println(key1);
    }

}
