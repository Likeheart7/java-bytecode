package com.chenx.jvmbytecode.type;

public class U4 {
    private byte[] value;

    public U4(byte u1, byte u2, byte u3, byte u4) {
        value = new byte[]{u1, u2, u3, u4};
    }

    /**
     * 作为32位二进制，计算对应的整数
     */
    public Integer toInt() {
        int a = (value[0] & 0xff) << 24;
        a |= (value[1] & 0xff) << 16;
        a |= (value[2] & 0xff) << 8;
        return a | (value[3] & 0xff);
    }

    /**
     * 每个byte作为两个十六进制表示的方法。
     */
    public String toHexString() {
        char[] hexChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder builder = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int v = value[i] & 0xff;
            while (v > 0) {
                int c = v % 16;
                v = v >>> 4;
                builder.insert(0, hexChar[c]);
            }
            if ((builder.length() & 0x01) == 1) {
                builder.insert(0, '0');
            }
        }
        return "0x" + builder.toString();
    }
}
