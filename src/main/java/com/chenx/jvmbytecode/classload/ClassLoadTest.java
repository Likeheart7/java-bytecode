package com.chenx.jvmbytecode.classload;

public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // 使用类加载器加载，不会触发静态代码块，也就是没调用<clinit>，通过参数resolve可以修改
        Class<?> clazz = ClassLoadTest.class.getClassLoader().loadClass("com.chenx.jvmbytecode.classload.classLoadDemo");
        // forName会触发静态代码块，也就是调用了<clinit>
//        Class<?> clazz = Class.forName("com.chenx.jvmbytecode.classload.classLoadDemo");
        System.out.println(clazz.getName());
    }
}

class classLoadDemo {
    static {
        System.out.println("this is classLoad demo");
    }
}