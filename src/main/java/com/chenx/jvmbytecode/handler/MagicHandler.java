package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.U4;

import java.nio.ByteBuffer;

/**
 * 解析魔数，魔数是class文件第0-3个字节
 */
public class MagicHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 0;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 连续读取四个字节
        classFile.setMagic(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
        if (!"0xCAFEBABE".equalsIgnoreCase(classFile.getMagic().toHexString())) {
            throw new Exception("魔数解析错误，该文件不是class文件。");
        }
    }
}
