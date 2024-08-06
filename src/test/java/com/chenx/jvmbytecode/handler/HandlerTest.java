package com.chenx.jvmbytecode.handler;


import com.chenx.jvmbytecode.type.ClassFile;
import com.chenx.jvmbytecode.type.constant.CpInfo;
import com.chenx.jvmbytecode.util.ClassFileAnalyzer;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class HandlerTest {
    @Test
    public void testVersionHandler() throws Exception {
        ByteBuffer byteBuffer = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\type\\U2.class");
        ClassFile classFile = ClassFileAnalyzer.Analysis(byteBuffer);
        System.out.println("魔数: "+classFile.getMagic().toHexString());
        System.out.println("副版本号: "+classFile.getMinor_version().toHexString());
        System.out.println("主版本号: "+classFile.getMajor_version().toHexString());
        System.out.println("常量池常量项总数：" + classFile.getConstant_pool_count().toInt());
        CpInfo[] constantPool = classFile.getConstant_pool();
        System.out.println("===逐个常量项信息===");
        for(CpInfo cpInfo : constantPool){
            System.out.println("=======>"+cpInfo.toString());
        }
    }
}
