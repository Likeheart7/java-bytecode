package com.chenx.jvmbytecode.type;

import com.chenx.jvmbytecode.classfileparse.type.U4;
import org.junit.jupiter.api.Test;

class U4Test {

    @Test
    void toInt() {
        U4 u4 = new U4((byte) 46, (byte) 26, (byte) 46, (byte) 26);
        System.out.println(u4.toInt());
    }

    @Test
    void toHexString() {
        U4 u4 = new U4((byte) 46, (byte) 26, (byte) 46, (byte) 26);
        System.out.println(u4.toHexString());
    }
}