package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 存储String类型的常量
 */
public class ConstantStringInfo extends CpInfo{

    // 值是常量池中某个常量的索引，必须指向一个ConstantUtf8Info常量
    private U2 stringIndex;

    public ConstantStringInfo(U1 tag) {
        super(tag);
    }
    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.stringIndex = new U2(codeBuf.get(), codeBuf.get());
    }
    @Override
    public String toString() {
        return "CONSTANT_String_info";
    }

}
