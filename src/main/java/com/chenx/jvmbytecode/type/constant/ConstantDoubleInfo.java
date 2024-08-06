package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;

/**
 * 存储双精度浮点数，结构和ConstantLongInfoi一样
 */
public class ConstantDoubleInfo extends ConstantLongInfo{
    public ConstantDoubleInfo(U1 tag) {
        super(tag);
    }


    @Override
    public String toString() {
        return "CONSTANT_Double_info";
    }
}
