package com.chenx.jvmbytecode.classfileparse.type.attribute;

import com.chenx.jvmbytecode.classfileparse.type.U2;
import com.chenx.jvmbytecode.classfileparse.type.U4;

/**
 * 用于告知虚拟机在类或接口的初始化阶段为被标志为ACC_STATIC/ACC_FINAL的静态常量字段自动赋值。
 * 接口中声明的字段，自动加上这两个标志
 * 只能是基本类型或String。
 */
public class ConstantValueAttribute {
    private U2 attributeNameIndex;  // 指向常量池中的CONSTANT_Utf8_info常量，该常量表示的字符串为“ConstantValue”；
    private U4 attributeLength; // ConstantValue属性是定长属性，因此attribute_length的值固定为2
    private U2 constantValueIndex; // 指向基本数据类型或String类型常量。

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

    public U2 getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(U2 constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }
}
