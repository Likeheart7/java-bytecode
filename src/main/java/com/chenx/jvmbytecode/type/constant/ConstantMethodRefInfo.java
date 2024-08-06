package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;

/**
 * 和ConstantFieldRefInfo一样
 */
public class ConstantMethodRefInfo extends ConstantFieldRefInfo{
    public ConstantMethodRefInfo(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_Methodref_info";
    }
}
