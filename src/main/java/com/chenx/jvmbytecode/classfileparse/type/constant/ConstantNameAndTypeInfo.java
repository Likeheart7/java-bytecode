package com.chenx.jvmbytecode.classfileparse.type.constant;

import com.chenx.jvmbytecode.classfileparse.type.U1;
import com.chenx.jvmbytecode.classfileparse.type.U2;

import java.nio.ByteBuffer;

/**
 * 存储字段的名称和类型描述符。或者用于存储方法的名称和方法的类型描述符。
 */
public class ConstantNameAndTypeInfo extends CpInfo{
    // 名称指向常量池中某个常量的索引，必须是ConstantUtf8Info类型
    private U2 nameIndex;
    // 描述符指向常量池中某个常量的索引，必须是ConstantUtf8Info类型
    private U2 descriptorIndex;

    public ConstantNameAndTypeInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 名称索引
        nameIndex = new U2(codeBuf.get(), codeBuf.get());
        // 描述符索引
        descriptorIndex = new U2(codeBuf.get(), codeBuf.get());
    }

    @Override
    public String toString() {
        return "CONSTANT_NameAndType_info";
    }
}
