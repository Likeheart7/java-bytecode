package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U4;

import java.nio.ByteBuffer;

/**
 * 存储一个整型数值
 */
public class ConstantIntegerInfo extends CpInfo{

    // 该值对应的十进制值，就是该常量对应的整型值
    private U4 bytes;

    public ConstantIntegerInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取连续四个字节
        this.bytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }

    @Override
    public String toString() {
        return "CONSTANT_Integer_info";
    }
}
