package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.ClassFile;

import java.nio.ByteBuffer;

/**
 * class所有结构解析器的父类，抽象出各个解析器的行为，包括
 * 1. 解析具体结构的方法
 * 2. 该解析器在解析器中的顺序
 */
public interface BaseByteCodeHandler {
    /**
     * 返回该解析器在所有解析器中的顺序，值越小，顺序越靠前。
     */
    int order();

    /**
     * 读取指定的.class文件，每个实现该接口的类都能通过该方法，从字节缓存读取相应的字节写入ClassFile对象。
     * @param codeBuf
     * @param classFile
     * @throws Exception
     */
    void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception;
}
