package com.chenx.jvmbytecode.factory;

import com.chenx.jvmbytecode.type.AttributeInfo;
import com.chenx.jvmbytecode.type.U4;
import com.chenx.jvmbytecode.type.attribute.CodeAttribute;
import com.chenx.jvmbytecode.type.attribute.ConstantValueAttribute;
import com.chenx.jvmbytecode.type.U2;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.nio.ByteBuffer;

public class AttributeProcessFactory {

    /**
     * 传入一个经过一次解析后的ConstantValue属性对象（AttributeInfo ），方法返回二次解析后生成的ConstantValue属性对象（ConstantValue_attribute）。
     * @param attributeInfo 一次解析后的属性对象
     * @return 二次解析后的属性对象
     */
    public static ConstantValueAttribute processingConstantValue(AttributeInfo attributeInfo) {
        ConstantValueAttribute attribute = new ConstantValueAttribute();
        attribute.setAttributeNameIndex(attributeInfo.getAttributeNameIndex());
        attribute.setAttributeLength(attributeInfo.getAttributeLength());
        attribute.setConstantValueIndex(new U2(attributeInfo.getInfo()[0], attributeInfo.getInfo()[1]));
        return attribute;
    }

    /**
     * 二次解析方法的Code属性
     * @param attributeInfo 一次解析的结果
     * @return 二次解析的结果
     */
    public static CodeAttribute processingCode(AttributeInfo attributeInfo) {
        CodeAttribute code = new CodeAttribute();
        ByteBuffer body = ByteBuffer.wrap(attributeInfo.getInfo());
        // 操作数栈大小
        code.setMax_stack(new U2(body.get(),body.get()));
        // 局部变量表大小
        code.setMax_locals(new U2(body.get(),body.get()));
        // 字节码数组长度
        code.setCode_length(new U4(body.get(),body.get(),body.get(),body.get()));
        // 解析获取字节码
        byte[] byteCode = new byte[code.getCode_length().toInt()];
        body.get(byteCode,0,byteCode.length);
        code.setCode(byteCode);
        return code;
    }
}
