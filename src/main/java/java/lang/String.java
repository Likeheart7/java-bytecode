package java.lang;

/**
 * 用于测试自定义类加载器，避开双亲委派，加载自己的String类
 */
public class String {
    @Override
    public String toString() {
        return "this is a customized String";
    }
}
