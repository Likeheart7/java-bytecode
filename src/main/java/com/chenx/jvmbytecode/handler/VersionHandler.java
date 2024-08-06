package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 版本号分为主版本号和副版本号。是class文件第4-7个字节
 */
public class VersionHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 1;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 先读副版本号的两个字节
        classFile.setMinor_version(new U2(codeBuf.get(), codeBuf.get()));
        // 再读主版本号的两个字节
        classFile.setMajor_version(new U2(codeBuf.get(), codeBuf.get()));
    }
}

