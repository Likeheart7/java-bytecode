package com.chenx.jvmbytecode.classfileparse.type.attribute;

import com.chenx.jvmbytecode.classfileparse.type.AttributeInfo;
import com.chenx.jvmbytecode.classfileparse.type.U4;
import com.chenx.jvmbytecode.classfileparse.type.U2;

/**
 * 用于对方法的属性进行二次解析。本地方法、抽象方法、非default的接口方法都不会有Code属性。实例初始化方法、类或接口的初始化方法都有Code属性
 * 方法结构的属性表最多一个Code属性，是可变长的，包含了方法的字节码指令及相关辅助方法。
 * Code属性的结构如下：
 * 字段名	                    类型	                说明
 * attribute_name_index	        u2	                指向常量池中“Code”常量的索引
 * attribute_length	            u4	                属性的长度（不包括attribute_name_index和attribute_length占用的长度）
 * max_stack	                u2	                操作数栈的最大深度
 * max_locals	                u2	                局部变量表的最大深度
 * code_length	                u4	                code数组的大小
 * code	                        u1[]	            存储字节码指令
 * exception_table_length	    u2	                异常表的长度
 * exception_table	            exception[]	        异常表
 * attributes_count         	u2	                属性总数
 * attributes	                attribute_info[]	属性表
 *
 * 其中exception结构为：
 * 字段名	        类型	    说明
 * start_pc	        u2	    try的开始位置，在code[]中的索引
 * end_pc	        u2	    try的结束位置，在code[]中的索引。
 * handler_pc	    u2	    异常处理的起点，在code[]中的索引。
 * catch_type	    u2	    为0相当finally块。不为0时，指向常量池中某个CONSTANT_Class_info常量的索引，表示需要捕获的异常，
 *                          只有[start_pc,end_pc)范围内抛出的异常是指定的类或子类的实例，才会跳转到handler_pc指向的字节码指令继续执行。
 */
public class CodeAttribute {
    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 max_stack;
    private U2 max_locals;
    private U4 code_length;
    private byte[] code;
    private U4 exception_table_length;
    private Exception[] exception_table;
    private U2 attributes_count;
    private AttributeInfo[] attributes;
    // 异常表中每项的结构
    public static class Exception {
        private U2 start_pc;
        private U2 end_pc;
        private U2 handler_pc;
        private U2 catch_type;
    }

    public U2 getAttribute_name_index() {
        return attribute_name_index;
    }

    public void setAttribute_name_index(U2 attribute_name_index) {
        this.attribute_name_index = attribute_name_index;
    }

    public U4 getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(U4 attribute_length) {
        this.attribute_length = attribute_length;
    }

    public U2 getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(U2 max_stack) {
        this.max_stack = max_stack;
    }

    public U2 getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(U2 max_locals) {
        this.max_locals = max_locals;
    }

    public U4 getCode_length() {
        return code_length;
    }

    public void setCode_length(U4 code_length) {
        this.code_length = code_length;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public U4 getException_table_length() {
        return exception_table_length;
    }

    public void setException_table_length(U4 exception_table_length) {
        this.exception_table_length = exception_table_length;
    }

    public Exception[] getException_table() {
        return exception_table;
    }

    public void setException_table(Exception[] exception_table) {
        this.exception_table = exception_table;
    }

    public U2 getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(U2 attributes_count) {
        this.attributes_count = attributes_count;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
