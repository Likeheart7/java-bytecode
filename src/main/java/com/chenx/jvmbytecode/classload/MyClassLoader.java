package com.chenx.jvmbytecode.classload;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Test {@link com.chenx.jvmbytecode.classload.MyClassLoaderTest}
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * 将findClass()方法开放(findClass()方法本身是protected类型的，不能在外界直接访问)，直接调用以获取class，绕过双亲委派机制。
     */
    @Override
    public Class<?> loadClass(final String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
