package com.chenx.jvmbytecode.classfileparse.type.constant;

import com.chenx.jvmbytecode.classfileparse.type.U1;

/**
 * 常量池数据信息
 */
public abstract class CpInfo implements ConstantInfoHandler {
    /*
     * tag属性用于标注常量是哪种常量类型
     * 常量类型	                        tag值	描述
     * CONSTANT_Class_info	            7	    类或接口的符号引用
     * CONSTANT_Fieldref_info	        9	    字段的符号引用
     * CONSTANT_Methodref_info	        10	    类中方法的符号引用
     * CONSTANT_InterfaceMethodref_info	11	    接口中方法的符号引用
     * CONSTANT_String_info	            8	    字符串类型字面量
     * CONSTANT_Integer_info	        3	    整型字面量
     * CONSTANT_Float_info	            4	    浮点型字面量
     * CONSTANT_Long_info	            5	    长整形字面类
     * CONSTANT_Double_info	            6	    双精度浮点型字面量
     * CONSTANT_NameAndType_info	    12	    字段与字段类型或方法与方法类型的符号引用
     * CONSTANT_Utf8_info	            1	    UTF-8编码字符串
     * CONSTANT_MethodHandle_info	    15	    表示方法句柄
     * CONSTANT_MethodType_info	        16	    表示方法类型
     * CONSTANT_InvokeDynamic_info	    18	    表示一个动态方法调用点
     */
    private U1 tag;
    public CpInfo(U1 tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "tag=U1{" + tag.toHexString() + "}";
    }


    /**
     * 用于根据tag值创建对应的常量类型对象
     * @param tag 标注常量是哪种类型的标识
     * @return 根据标识创建的对应的车辆类型对象
     */
    public static CpInfo newCpInfo(U1 tag) throws Exception {
        int tagValue = tag.toInt();
        CpInfo info;
        switch (tagValue) {
            case 1:
                info = new ConstantUtf8Info(tag);
                break;
            case 3:
                info = new ConstantIntegerInfo(tag);
                break;
            case 4:
                info = new ConstantFloatInfo(tag);
                break;
            case 5:
                info = new ConstantLongInfo(tag);
                break;
            case 6:
                info = new ConstantDoubleInfo(tag);
                break;
            case 7:
                info = new ConstantClassInfo(tag);
                break;
            case 8:
                info = new ConstantStringInfo(tag);
                break;
            case 9:
                info = new ConstantFieldRefInfo(tag);
                break;
            case 10:
                info = new ConstantMethodRefInfo(tag);
                break;
            case 11:
                info = new ConstantInterfaceMethodRefInfo(tag);
                break;
            case 12:
                info = new ConstantNameAndTypeInfo(tag);
                break;
            case 15:
                info = new ConstantMethodHandleInfo(tag);
                break;
            case 16:
                info = new ConstantMethodTypeInfo(tag);
                break;
            case 18:
                info = new ConstantInvokeDynamicInfo(tag);
                break;
            default:
                throw new Exception("没有找到该TAG=" + tagValue + "对应的常量类型");
        }
        return info;
    }
}
