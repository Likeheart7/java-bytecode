package com.chenx.jvmbytecode.type;

import com.chenx.jvmbytecode.classfileparse.type.U2;
import org.junit.jupiter.api.Test;

public class U2Test {
    @Test
    public void testToHexString() {
        U2 u2 = new U2(Byte.parseByte("46"), Byte.parseByte("26"));
        System.out.println(u2.toHexString());
    }
    @Test
    public void testToInt() {
        U2 u2 = new U2(Byte.parseByte("46"), Byte.parseByte("26"));
        System.out.println(u2.toInt());
    }
}
