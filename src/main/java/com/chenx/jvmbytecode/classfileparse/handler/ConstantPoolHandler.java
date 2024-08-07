package com.chenx.jvmbytecode.classfileparse.handler;

import com.chenx.jvmbytecode.classfileparse.type.ClassFile;
import com.chenx.jvmbytecode.classfileparse.type.U1;
import com.chenx.jvmbytecode.classfileparse.type.U2;
import com.chenx.jvmbytecode.classfileparse.type.constant.ConstantLongInfo;
import com.chenx.jvmbytecode.classfileparse.type.constant.CpInfo;

import java.nio.ByteBuffer;

/**
 * 常量池解析器。
 */
public class ConstantPoolHandler implements BaseByteCodeHandler{
    /**
     * 常量池解析器跟在版本解析器后面
     */
    @Override
    public int order() {
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 读取常量池计数器
        U2 cpLen = new U2(codeBuf.get(), codeBuf.get());
        classFile.setConstant_pool_count(cpLen);
        // 常量池计数器-1，得到常量池常量总数，根据总数，创建常量池表
        // 注：常量池的常量总数并不是等于constant_pool_count-1个，而应该是(constant_pool_count - 1 - (CONSTANT_Long_info常量个数+CONSTANT_Double_info常量个数))
        int coInfoLen = cpLen.toInt() - 1;
        classFile.setConstant_pool(new CpInfo[coInfoLen]);
        for (int i = 0; i < coInfoLen; i++) {
            // 读取常量结构标识符tag
            U1 tag = new U1(codeBuf.get());
            // 根据tag创建对应的常量类型对象
            CpInfo cpInfo = CpInfo.newCpInfo(tag);
            // 执行常量对象的read方法解析常量
            cpInfo.read(codeBuf);
//            System.out.println("#" + (i + 1) + ":" + cpInfo);
            // 设置ClassFile对象的常量池内容
            classFile.getConstant_pool()[i] = cpInfo;
            // 遇到CONSTANT_Long_info常量或是CONSTANT_Double_info常量时，下一个常量不读取，直到跳到下下个常量。
            if (cpInfo instanceof ConstantLongInfo) {
                i++;
            }
        }
    }
}
