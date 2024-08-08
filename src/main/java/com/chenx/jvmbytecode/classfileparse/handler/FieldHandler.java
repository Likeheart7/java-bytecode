package com.chenx.jvmbytecode.classfileparse.handler;

import com.chenx.jvmbytecode.classfileparse.type.*;

import java.nio.ByteBuffer;

/**
 * 字段表解析器。也解析属性字段结构的属性表。
 */
public class FieldHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 6;
    }

    /**
     * 解析步骤可以分为以下几步：
     * 1、先从class文件字节缓存中读取到字段总数，根据字段总数创建字段表；
     * 2、循环解析出每个字段；
     * 3、解析字段的属性表时，先解析获取到属性总数，再根据属性总数创建属性表；
     * 4、使用通用属性结构循环解析出字段的每个属性；
     * 5、解析属性时，先解析出attribute_name_index，再解析attribute_length获取属性info的长度，根据长度读取指定长度的字节数据存放到属性的info字段。
     */
    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 fieldCountU2 = new U2(codeBuf.get(), codeBuf.get());
        classFile.setFields_count(fieldCountU2);
        Integer fieldCount = fieldCountU2.toInt();
        // 没有属性，直接结束
        if (fieldCount == 0) {
            return;
        }
        FieldInfo[] fieldInfos = new FieldInfo[fieldCount];
        classFile.setFields(fieldInfos);
        // 逐个解析字段
        for (int i = 0; i < fieldCount; i++) {
            FieldInfo curFieldInfo = new FieldInfo();
            curFieldInfo.setAccessFlag(new U2(codeBuf.get(), codeBuf.get()));
            curFieldInfo.setNameIndex(new U2(codeBuf.get(), codeBuf.get()));
            curFieldInfo.setDescriptorIndex(new U2(codeBuf.get(), codeBuf.get()));
            U2 attributeCountU2 = new U2(codeBuf.get(), codeBuf.get());
            curFieldInfo.setAttributeCount(attributeCountU2);
            fieldInfos[i] = curFieldInfo;
            Integer attributeCount = attributeCountU2.toInt();
            // 没有属性，直接跳出本次循环
            if (attributeCount <= 0) {
                continue;
            }
            AttributeInfo[] attributeInfos = new AttributeInfo[attributeCount];
            curFieldInfo.setAttributes(attributeInfos);
            // 循环解析出每个属性，先使用通用属性结构
            for (int j = 0; j < attributeCount; j++) {
                attributeInfos[j] = new AttributeInfo();
                // 字段属性
                attributeInfos[j].setAttributeNameIndex(new U2(codeBuf.get(), codeBuf.get()));
                // 属性info的长度
                U4 attrLength = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
                attributeInfos[j].setAttributeLength(attrLength);
                // 解析info，属性信息
                byte[] info = new byte[attrLength.toInt()];
                codeBuf.get(info, 0, info.length);
                attributeInfos[j].setInfo(info);
            }
        }
    }
}
