package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;

//存储方法句柄，这是虚拟机为实现动态调用指令（invokedynamic）所增加的常量结构。
public class ConstantMethodHandleInfo extends CpInfo{
    /*
    reference_kind取值	        reference_index指向的常量项的类型
    1（REF_getField）	        CONSTANT_Fieldref_info
    2（REF_getStatic）	        CONSTANT_Fieldref_info
    3（REF_putField）	        CONSTANT_Fieldref_info
    4（REF_putStatic）	        CONSTANT_Fieldref_info
    5（REF_invokeVirtual）	    CONSTANT_Methodref_info
    6（REF_invokeStatic）	    CONSTANT_Methodref_info CONSTANT_InterfaceMethodref_info
    7（REF_invokeSpecial）	    CONSTANT_Methodref_info CONSTANT_InterfaceMethodref_info
    8（REF_newInvokeSpecial）	CONSTANT_Methodref_info
    9（REF_invokeInterface）	    CONSTANT_InterfaceMethodref_info
     */
    // 取值范围[1,9]，表示方法的句柄类型
    private U1 referenceKind;
    // 指向常量池中某个常量的索引
    private U2 referenceIndex;

    public ConstantMethodHandleInfo(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.referenceKind = new U1(codeBuf.get());
        this.referenceIndex = new U2(codeBuf.get(), codeBuf.get());
    }
    @Override
    public String toString() {
        return "CONSTANT_MethodHandle_info";
    }
}
