package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 也是为了实现invokedynamic指令增加的常量结构
 */
public class ConstantMethodTypeInfo extends CpInfo {
    // 指向常量池中某个ContantUtf8Info结构的常量
    private U2 descriptorIndex;

    public ConstantMethodTypeInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.descriptorIndex = new U2(codeBuf.get(), codeBuf.get());
    }
    @Override
    public String toString() {
        return "CONSTANT_MethodType_info";
    }
}
