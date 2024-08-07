package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 接口表解析器。
 * 在class文件中，继this_class与super_class之后，存储的就是该class实现的接口总数以及该class实现的所有接口。
 */
public class InterfaceHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 5;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 先读取最前面代表接口总数的两个字节
        U2 interfaceCountObj = new U2(codeBuf.get(), codeBuf.get());
        classFile.setInterfaces_count(interfaceCountObj);
        // 转为整型
        Integer interfaceCount = classFile.getInterfaces_count().toInt();
        U2[] interfaces = new U2[interfaceCount];
        classFile.setInterfaces(interfaces);
        // 逐个读取两个字节的interface信息
        for (int i = 0; i < interfaceCount; i++) {
            interfaces[i] = new U2(codeBuf.get(), codeBuf.get());
        }
    }
}
