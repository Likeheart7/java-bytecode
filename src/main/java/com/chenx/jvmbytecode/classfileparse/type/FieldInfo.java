package com.chenx.jvmbytecode.classfileparse.type;

public class FieldInfo {
    // 访问标志
    private U2 accessFlag;
    // 指向常量池中某个常量的索引
    private U2 nameIndex;
    // 类型描述符，指向常量池中某个常量的索引
    private U2 descriptorIndex;
    // 属性总数
    private U2 attributeCount;
    // 属性
    private AttributeInfo[] attributes;

    public U2 getAccessFlag() {
        return accessFlag;
    }

    public U2 getNameIndex() {
        return nameIndex;
    }

    public U2 getDescriptorIndex() {
        return descriptorIndex;
    }

    public U2 getAttributeCount() {
        return attributeCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAccessFlag(U2 accessFlag) {
        this.accessFlag = accessFlag;
    }

    public void setNameIndex(U2 nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setDescriptorIndex(U2 descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public void setAttributeCount(U2 attributeCount) {
        this.attributeCount = attributeCount;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
