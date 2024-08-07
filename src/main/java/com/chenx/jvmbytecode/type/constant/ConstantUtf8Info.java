package com.chenx.jvmbytecode.type.constant;

import com.chenx.jvmbytecode.type.U1;
import com.chenx.jvmbytecode.type.U2;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 用于存储字符串常量，字符串编码使用UTF-8
 * 几乎所有的常量类型内的属性最终指向的都是该类型
 * {
 *   u1  tag;
 *   u2  length;
 *   u1[]  bytes;
 * }
 */
public class ConstantUtf8Info extends CpInfo{
    // 描述字符串字节数组的长度
    private U2 length;
    private byte[] bytes;

    public ConstantUtf8Info(U1 tag) {
        super(tag);
    }

    /**
     * 从class文件对应的ByteBuffer对象中读取该类型的常量，需要按顺序先读取长度，再根据长度n取后续n哥字节，存放到该常量的字节数组中。
     * @param codeBuf
     * @throws Exception
     */
    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取两字节长度
        this.length = new U2(codeBuf.get(), codeBuf.get());
        // 根据长度读取自订的字节
        this.bytes = new byte[length.toInt()];
        codeBuf.get(this.bytes, 0, length.toInt());
    }

    @Override
    public String toString() {
        return super.toString() +
                ",length=" + length.toInt() +
                ",str=" + new String(bytes, StandardCharsets.UTF_8);
    }

    public byte[] getBytes() {
        return bytes;
    }
}
