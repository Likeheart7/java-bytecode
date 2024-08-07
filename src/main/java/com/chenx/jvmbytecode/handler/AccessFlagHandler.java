package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * AccessFlag表示class文件对应的类的访问修饰符，在常量池之后，占两个字节。
 */
public class AccessFlagHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 3;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAccess_flag(new U2(codeBuf.get(), codeBuf.get()));
    }
}
