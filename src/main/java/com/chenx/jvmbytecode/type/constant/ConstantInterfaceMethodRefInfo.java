package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;

/**
 * 和ConstantFieldRefInfo一样
 */
public class ConstantInterfaceMethodRefInfo extends ConstantFieldRefInfo{
    public ConstantInterfaceMethodRefInfo(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_InterfaceMethodref_info";
    }
}
