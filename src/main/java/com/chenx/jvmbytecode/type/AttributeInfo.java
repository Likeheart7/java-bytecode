package com.chenx.jvmbytecode.type;

/**
 * 通用属性结构，方法和字段都有。包括字段的ConstantValue、方法的Code等。
 */
public class AttributeInfo {
    // 属性名称，指向常量池中某个常量的索引
    private U2 attributeNameIndex;
    // 属性长度，属性的字节总数
    private U4 attributeLength;
    // 属性信息，不同的属性用不同的解析
    private byte[] info;


    public U2 getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(U2 attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public U4 getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(U4 attributeLength) {
        this.attributeLength = attributeLength;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }
}
