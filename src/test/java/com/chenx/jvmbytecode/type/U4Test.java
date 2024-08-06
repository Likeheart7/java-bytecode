package com.chenx.jvmbytecode.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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