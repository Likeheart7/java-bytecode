package com.chenx.jvmbytecode.handler;

import com.chenx.jvmbytecode.type.AttributeInfo;
import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.U2;
import com.chenx.jvmbytecode.type.U4;

import java.nio.ByteBuffer;

/**
 * 属性表解析器
 * 方法结构和字段结构都有属性表，这些属性表有一个通用的结构。
 */
public class AttributeHandler implements BaseByteCodeHandler{

    @Override
    public int order() {
        return 8;
    }

    /**
     * 解析步骤如下：
     * 1. 读取接下来的两个字节，表示该class文件的属性表长度。
     * 2. 使用通用属性结构循环解析出每个属性。
     */
    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        Integer attrInfoLength = classFile.getAttributes_count().toInt();
        if (attrInfoLength <= 0) {
            return;
        }
        AttributeInfo[] attributeInfos = new AttributeInfo[attrInfoLength];
        classFile.setAttributes(attributeInfos);
        for (int i = 0; i < attrInfoLength; i++) {
            AttributeInfo curAttributeInfo = new AttributeInfo();
            attributeInfos[i] = curAttributeInfo;
            curAttributeInfo.setAttributeNameIndex(new U2(codeBuf.get(), codeBuf.get()));
            curAttributeInfo.setAttributeLength(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
            Integer attrLength = curAttributeInfo.getAttributeLength().toInt();
            if (attrLength <= 0) {
                continue;
            }
            byte[] bytes = new byte[attrLength];
            codeBuf.get(bytes, 0, attrLength);
            curAttributeInfo.setInfo(bytes);
        }
    }
}
