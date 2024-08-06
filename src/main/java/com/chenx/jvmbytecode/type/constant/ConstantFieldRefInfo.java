package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 存储字段的符号信息
 */
public class ConstantFieldRefInfo extends CpInfo{
    // 指向的常量必须是一个ConstantClassInfo常量，表示当前字段所在类的类名
    private U2 classIndex;
    // 指向的常量必须是一个ConstantNameAndTypeInfo常量，表示当前字段的名字和类型描述符。
    private U2 nameAndTypeIndex;

    public ConstantFieldRefInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.classIndex = new U2(codeBuf.get(), codeBuf.get());
        this.nameAndTypeIndex = new U2(codeBuf.get(), codeBuf.get());
    }


    @Override
    public String toString() {
        return "CONSTANT_Fieldref_info";
    }
}
