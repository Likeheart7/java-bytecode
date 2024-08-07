package com.chenx.jvmbytecode.classfileparse.type.constant;

import com.chenx.jvmbytecode.classfileparse.type.U1;

/**
 * 存储的是浮点数，结构和ConstantIntegerInfo一样
 */
public class ConstantFloatInfo extends ConstantIntegerInfo {
    public ConstantFloatInfo(U1 tag) {
        super(tag);
    }

    @Override
    public String toString() {
        return "CONSTANT_Float_info";
    }
}
