package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 存储类的符号信息
 */
public class ConstantClassInfo extends CpInfo{
    // 指向常量池中某一常量的索引字段，该字段指向的常量必须是一个ConstantUtf8Info常量，该常量存储的是class的内部类名(使用“/”替换“.“)的类名
    private U2 nameIndex;

    public ConstantClassInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.nameIndex = new U2(codeBuf.get(), codeBuf.get());
    }

    @Override
    public String toString() {
        return "CONSTANT_Class_info{" +
                "name_index=" + nameIndex.toInt() +
                '}';
    }

    public U2 getNameIndex() {
        return nameIndex;
    }
}
