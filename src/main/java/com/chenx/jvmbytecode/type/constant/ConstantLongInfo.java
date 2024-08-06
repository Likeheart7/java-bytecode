package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U4;

import java.nio.ByteBuffer;

/**
 * 存储的是Long类型的长整型值，使用8位字节存储，分为高32位和低32位
 */
public class ConstantLongInfo extends CpInfo{
    private U4 highBytes;
    private U4 lowBytes;

    public ConstantLongInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取高位4字节
        this.highBytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
        // 读取低位四字节
        this.lowBytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }


    @Override
    public String toString() {
        return "CONSTANT_Long_info";
    }
}
