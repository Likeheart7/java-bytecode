package com.chenx.jvmbytecode.type;

/**
 * 方法表结构。方法编译后的字节码指令是存放在方法结构的属性表中的，对应Code属性。但不是所有方法都会有Code属性，
 * 如接口中的方法不一定会有Code属性，如抽象方法一定没有Code属性。方法包括静态方法、以及类的初始化方法和类的实例初始化方法。
 */
public class MethodInfo {
    private U2 accessFlags;
    private U2 nameIndex;
    private U2 descriptorIndex;
    private U2 attributeCount;
    private AttributeInfo[] attributes;

    public U2 getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(U2 accessFlags) {
        this.accessFlags = accessFlags;
    }

    public U2 getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(U2 nameIndex) {
        this.nameIndex = nameIndex;
    }

    public U2 getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(U2 descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public U2 getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(U2 attributeCount) {
        this.attributeCount = attributeCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
