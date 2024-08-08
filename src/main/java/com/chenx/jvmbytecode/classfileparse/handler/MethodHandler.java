package com.chenx.jvmbytecode.classfileparse.handler;

import com.chenx.jvmbytecode.classfileparse.type.*;

import java.nio.ByteBuffer;

public class MethodHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 7;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setMethod_count(new U2(codeBuf.get(), codeBuf.get()));
        Integer methodCount = classFile.getMethod_count().toInt();
        // 方法不为空，直接结束
        if (methodCount <= 0) {
            return;
        }

        // 创建方法表
        MethodInfo[] methodInfos = new MethodInfo[methodCount];
        classFile.setMethods(methodInfos);
        for (int i = 0; i < methodCount; i++) {
            // 解析方法
            MethodInfo curMethodInfo = new MethodInfo();
            curMethodInfo.setAccessFlags(new U2(codeBuf.get(), codeBuf.get()));
            curMethodInfo.setNameIndex(new U2(codeBuf.get(), codeBuf.get()));
            curMethodInfo.setDescriptorIndex(new U2(codeBuf.get(), codeBuf.get()));
            curMethodInfo.setAttributeCount(new U2(codeBuf.get(), codeBuf.get()));
            methodInfos[i] = curMethodInfo;
            // 方法的属性总数
            Integer attributeCount = curMethodInfo.getAttributeCount().toInt();
            if (attributeCount <=0) {
                continue;
            }
            // 解析方法的属性表
            AttributeInfo[] attributeInfos = new AttributeInfo[attributeCount];
            methodInfos[i].setAttributes(attributeInfos);
            for (int j = 0; j < attributeCount; j++) {
                AttributeInfo curAttributeInfo = new AttributeInfo();
                attributeInfos[j] = curAttributeInfo;
                // 解析方法的属性
                curAttributeInfo.setAttributeNameIndex(new U2(codeBuf.get(), codeBuf.get()));
                curAttributeInfo.setAttributeLength(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
                // 获取属性info的长度
                Integer attributeLength = curAttributeInfo.getAttributeLength().toInt();
                if (attributeLength <= 0 ) {
                    continue;
                }
                byte[] info = new byte[attributeLength];
                codeBuf.get(info, 0, attributeLength);
                curAttributeInfo.setInfo(info);
            }
        }
    }
}
