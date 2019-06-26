package lambda;

/**
 * @author yujiaqi
 * @date 2019-04-20 22:46
 * @description
 */
@FunctionalInterface
public interface MyPredicate<T> {
    boolean action(T t);
}
