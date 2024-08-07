package com.chenx.jvmbytecode.classfileparse.type;

/**
 * 实际是长度为两个字节的数组
 */
public class U2 {
    private byte[] value;


    public U2(byte u1, byte u2) {
        value = new byte[]{u1, u2};
    }

    /**
     * 将byte数组中的两个字节作为16位二进制，计算对应的整数
     */
    public Integer toInt() {
        return (value[0] & 0xff) << 8 | (value[1] & 0xff);
    }

    /**
     * 将输出数组内的byte作为十六进制输出
     */
    public String toHexString() {
        char[] hexChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i >= 0; i--) {
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
        return "0x" + ((builder.length() == 0) ? "00" : builder.toString());
    }
}
