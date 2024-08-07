package com.chenx.jvmbytecode.handler;


import com.chenx.jvmbytecode.classfileparse.factory.AttributeProcessFactory;
import com.chenx.jvmbytecode.classfileparse.type.*;
import com.chenx.jvmbytecode.classfileparse.type.constant.*;
import com.chenx.jvmbytecode.classfileparse.type.attribute.CodeAttribute;
import com.chenx.jvmbytecode.classfileparse.type.attribute.ConstantValueAttribute;
import com.chenx.jvmbytecode.classfileparse.util.ClassAccessFlagUtil;
import com.chenx.jvmbytecode.classfileparse.util.ClassFileAnalyzer;
import com.chenx.jvmbytecode.classfileparse.util.FieldAccessFlagUtils;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class HandlerTest {
    @Test
    public void testVersionHandler() throws Exception {
        ByteBuffer byteBuffer = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\type\\U2.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(byteBuffer);
        System.out.println("魔数: " + classFile.getMagic().toHexString());
        System.out.println("副版本号: " + classFile.getMinor_version().toHexString());
        System.out.println("主版本号: " + classFile.getMajor_version().toHexString());
        System.out.println("常量池常量项总数：" + classFile.getConstant_pool_count().toInt());
        CpInfo[] constantPool = classFile.getConstant_pool();
        System.out.println("===逐个常量项信息===");
        for (CpInfo cpInfo : constantPool) {
            System.out.println("=======>" + cpInfo.toString());
        }
        U2 accessFlag = classFile.getAccess_flag();
        System.out.println("访问修饰符：" + ClassAccessFlagUtil.toClassAccessFlagString(accessFlag));

        // this和super解析
        U2 thisClass = classFile.getThis_class();
        U2 superClass = classFile.getSuper_class();
        // 去常量池取具体的ConstantClassInfo常量
        ConstantClassInfo thisClassConstantClassInfo = (ConstantClassInfo) constantPool[thisClass.toInt() - 1];
        ConstantClassInfo superClassConstantClassInfo = (ConstantClassInfo) constantPool[superClass.toInt() - 1];
        // 根据ConstantClassInfo从常量池获取ConstantUtf8Info
        ConstantUtf8Info thisClassName = (ConstantUtf8Info) constantPool[thisClassConstantClassInfo.getNameIndex().toInt() - 1];
        ConstantUtf8Info superClassName = (ConstantUtf8Info) constantPool[superClassConstantClassInfo.getNameIndex().toInt() - 1];
        System.out.println("thisClassName: " + thisClassName);  // tag=U1{0x01},length=29,str=com/chenx/jvmbytecode/type/U2
        System.out.println("superClassName: " + superClassName); // tag=U1{0x01},length=16,str=java/lang/Object
        System.out.println();
    }

    @Test
    public void testInterfaceHandler() throws Exception {
        ByteBuffer fileInfo = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\handler\\InterfaceHandler.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(fileInfo);
        // 接口信息
        U2 interfacesCount = classFile.getInterfaces_count();
        System.out.println("接口总数：" + interfacesCount.toInt());
        // 有接口，逐个打印
        if (interfacesCount.toInt() != 0) {
            U2[] interfaces = classFile.getInterfaces();
            CpInfo[] constantPool = classFile.getConstant_pool();
            for (U2 interfaceIndex : interfaces) {
                // 根据索引从常量池找到对应的ConstantClassInfo常量
                ConstantClassInfo interfaceClassInfo = (ConstantClassInfo) constantPool[interfaceIndex.toInt() - 1];
                // 根据ConstantClassInfo常量的nameIndex从常量池找到对应的ConstantUtf8Info常量
                ConstantUtf8Info interfaceClassName = (ConstantUtf8Info) constantPool[interfaceClassInfo.getNameIndex().toInt() - 1];
                System.out.println(interfaceClassName); // tag=U1{0x01},length=49,str=com/chenx/jvmbytecode/handler/BaseByteCodeHandler

            }
        }
    }


    /**
     * 通过nameIndex从常量池中获取到对应的ConstantUtf8Info类型的常量的名称
     *
     * @param nameIndex 名称索引
     * @param classFile class文件
     */
    private static String getName(U2 nameIndex, ClassFile classFile) {
        ConstantUtf8Info name_info = (ConstantUtf8Info)
                classFile.getConstant_pool()[nameIndex.toInt() - 1];
        return name_info.toString();
    }

    @Test
    public void testFieldHandler() throws Exception {
        ByteBuffer byteBuffer = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\type\\AttributeInfo.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(byteBuffer);
        System.out.println("字段总数：" + classFile.getFields_count().toInt());
        // 逐个打印字段
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo fieldInfo : fieldInfos) {
            System.out.println();
            System.out.println("访问标志和属性：" + FieldAccessFlagUtils.toFieldAccessFlagsString(fieldInfo.getAccessFlag()));
            System.out.println("字段名：" + getName(fieldInfo.getNameIndex(), classFile));
            System.out.println("字段的类型描述符：" + getName(fieldInfo.getDescriptorIndex(), classFile));
            System.out.println("属性总数：" + fieldInfo.getAttributeCount().toInt());
        }
    }

    @Test
    public void testMethodHandler() throws Exception {
        ByteBuffer byteBuffer = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\type\\AttributeInfo.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(byteBuffer);
        System.out.println("方法总数：" + classFile.getMethod_count());
        MethodInfo[] methods = classFile.getMethods();
        for (MethodInfo methodInfo : methods) {
            System.out.println();
            System.out.println("访问标志和属性：" + FieldAccessFlagUtils.toFieldAccessFlagsString(methodInfo.getAccessFlags()));
            System.out.println("方法名：" + getName(methodInfo.getNameIndex(), classFile));
            System.out.println("方法描述符：" + getName(methodInfo.getDescriptorIndex(), classFile));
            System.out.println("属性总数：" + methodInfo.getAttributeCount());
        }
    }

    @Test
    public void testAllHandler() throws Exception {
        ByteBuffer byteBuffer = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\type\\AttributeInfo.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(byteBuffer);
    }

    /**
     * 测试ConstantValue属性的二次解析
     */
    @Test
    public void testConstantValueParse() throws Exception {
        ByteBuffer codeBuf = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\testusing\\TestInterface.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(codeBuf);
        // 获取所有字段
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo fieldInfo : fieldInfos) {
            // 获取字段的所有属性
            AttributeInfo[] attributeInfos = fieldInfo.getAttributes();
            if (attributeInfos == null || attributeInfos.length == 0) {
                continue;
            }
            System.out.println("字段：" + classFile.getConstant_pool()
                    [fieldInfo.getNameIndex().toInt() - 1]);
            // 遍历所有属性
            for (AttributeInfo attributeInfo : attributeInfos) {
                // 获取属性的名称
                U2 name_index = attributeInfo.getAttributeNameIndex();
                ConstantUtf8Info name_info = (ConstantUtf8Info)
                        classFile.getConstant_pool()[name_index.toInt() - 1];
                String name = new String(name_info.getBytes());
                // 如果属性名是ConstantValue，则对该属性进行二次解析
                if (name.equalsIgnoreCase("ConstantValue")) {
                    // 属性二次解析
                    ConstantValueAttribute constantValue = AttributeProcessFactory.processingConstantValue(attributeInfo);
                    // 取得constantvalue_index，从常量池中取值
                    U2 cv_index = constantValue.getConstantValueIndex();
                    Object cv = classFile.getConstant_pool()[cv_index.toInt() - 1];
                    // 需要判断常量的类型
                    if (cv instanceof ConstantUtf8Info) {
                        System.out.println("ConstantValue：" + cv.toString());
                    } else if (cv instanceof ConstantIntegerInfo) {
                        System.out.println("ConstantValue：" +
                                ((ConstantIntegerInfo) cv).getBytes().toInt());
                    } else if (cv instanceof ConstantIntegerInfo) {
                        // todo
                    } else if (cv instanceof ConstantFloatInfo) {
                        // todo
                    } else if (cv instanceof ConstantLongInfo) {
                        // todo
                    } else if (cv instanceof ConstantDoubleInfo) {
                        // todo
                    }
                }
            }
        }
    }

    /**
     * 测试方法结构的Code属性的二次解析
     */
    @Test
    public void testCodeAttributeParse() throws Exception {
        ByteBuffer codeBuf = ClassFileAnalyzer.readFile("D:\\programming\\java\\project\\java-bytecode\\target\\classes\\com\\chenx\\jvmbytecode\\testusing\\RecursionAlgorithmMain.class");
        ClassFile classFile = ClassFileAnalyzer.analysis(codeBuf);
        // 获取方法表
        MethodInfo[] methodInfos = classFile.getMethods();
        // 遍历方法表
        for (MethodInfo methodInfo : methodInfos) {
            // 获取方法的属性表
            AttributeInfo[] attributeInfos = methodInfo.getAttributes();
            if (attributeInfos == null || attributeInfos.length == 0) {
                continue;
            }
            System.out.println("方法：" + classFile.getConstant_pool()
                    [methodInfo.getNameIndex().toInt() - 1]);
            // 遍历属性表
            for (AttributeInfo attributeInfo : attributeInfos) {
                // 获取属性的名称
                U2 name_index = attributeInfo.getAttributeNameIndex();
                ConstantUtf8Info name_info = (ConstantUtf8Info)
                        classFile.getConstant_pool()[name_index.toInt() - 1];
                String name = new String(name_info.getBytes());
                // 对Code属性二次解析
                if (name.equalsIgnoreCase("Code")) {
                    // 属性二次解析
                    CodeAttribute code = AttributeProcessFactory.processingCode(attributeInfo);
                    System.out.println("操作数栈大小：" + code.getMax_stack().toInt());
                    System.out.println("局部变量表大小：" + code.getMax_locals().toInt());
                    System.out.println("字节码数组长度：" + code.getCode_length().toInt());
                    System.out.println("字节码：");
                    for (byte b : code.getCode()) {
                        System.out.print(Integer.toHexString((b & 0xff)) + " ");
                    }
                    System.out.println("\n");
                }
            }
        }
    }
}
