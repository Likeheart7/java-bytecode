package com.chenx.jvmbytecode.classload;

import org.junit.jupiter.api.Test;

import java.net.URL;

class MyClassLoaderTest {
    @Test
    public void testCustomizedClassLoader() throws Exception {
        // 正常情况下加载String
//        Class<?> clazz = MyClassLoaderTest.class.getClassLoader().loadClass("java.lang.String");
//        Constructor<?> constructor = clazz.getConstructor(char[].class);
//        String string = (String) constructor.newInstance(new char[]{'a', 'b','c'});
//        System.out.println(string);
        // 加载自定义的String，这里是class文件的目录
        //java.lang.SecurityException: Prohibited package name: java.lang Java虚拟机不允许java开头的报名，所以是无法自定义java.lang.String的
        MyClassLoader myClassLoader = new MyClassLoader(new URL[]{new URL("file:///D:\\programming\\java\\project\\java-bytecode\\target\\classes\\")});
        Class<?> clz = myClassLoader.loadClass("java.lang.String");
        String str = (String) clz.newInstance();
        System.out.println(str);
    }
}