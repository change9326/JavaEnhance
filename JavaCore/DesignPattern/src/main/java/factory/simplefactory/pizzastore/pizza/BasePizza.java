package factory.simplefactory.pizzastore.pizza;

/**
 * @author yujiaqi
 * @date 2019-09-15 10:59
 * @description
 */
public abstract class BasePizza {

        //名字
        protected String name;

        //准备原材料, 不同的披萨不一样，因此，我们做成抽象方法
        public abstract void prepare();


        public void bake() {
            System.out.println(name + " baking;");
        }

        public void cut() {
            System.out.println(name + " cutting;");
        }

        //打包
        public void box() {
            System.out.println(name + " boxing;");
        }

        public void setName(String name) {
            this.name = name;
        }
}