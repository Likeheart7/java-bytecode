package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

/**
 * 表示invokedynamic指令用到的引导方法bootstrap method，以及引导方法所用到的动态调用名称，参数，返回类型
 */
public class ConstantInvokeDynamicInfo extends CpInfo{
    // 指向class文件结构属性表中，引导方法表的某个引导方法
    private U2 bootstrapMethodAttrIndex;
    // 指向常量池中某个ConstantNameAndTypeInfo结构的常量
    private U2 nameAndTypeIndex;

    public ConstantInvokeDynamicInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.bootstrapMethodAttrIndex = new U2(codeBuf.get(), codeBuf.get());
        this.nameAndTypeIndex = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_and_type_index() {
        return nameAndTypeIndex;
    }

    public U2 getBootstrap_method_attr_index() {
        return bootstrapMethodAttrIndex;
    }

    @Override
    public String toString() {
        return "CONSTANT_InvokeDynamic_info";
    }
}
