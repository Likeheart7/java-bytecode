package com.chenx.jvmbytecode.type;

import com.chenx.jvmbytecode.type.constant.CpInfo;

/**
 * 用于保存class文件最终解析出来的结果
 */
public class ClassFile {
    private U4 magic;
    private U2 minor_version;
    private U2 major_version;
    private U2 constant_pool_count;
    private CpInfo[] constant_pool; // 常量池数据
    private U2 access_flag;
    private U2 this_class;  // 类索引
    private U2 super_class; // 父类索引
    private U2 interfaces_count;
    private U2[] interfaces; // 接口数组
    private U2 fields_count;
    private FieldInfo[] fields; // 字段表
    private U2 method_count; // 方法总数
    private MethodInfo[] methods; //方法表
    private U2 attributes_count;    //属性总数，属性和字段的区别是
    private AttributeInfo[] attributes; // 属性表

    public U4 getMagic() {
        return magic;
    }

    public void setMagic(U4 magic) {
        this.magic = magic;
    }

    public U2 getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(U2 minor_version) {
        this.minor_version = minor_version;
    }

    public U2 getMajor_version() {
        return major_version;
    }

    public void setMajor_version(U2 major_version) {
        this.major_version = major_version;
    }

    public U2 getConstant_pool_count() {
        return constant_pool_count;
    }

    public void setConstant_pool_count(U2 constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
    }

    public CpInfo[] getConstant_pool() {
        return constant_pool;
    }

    public void setConstant_pool(CpInfo[] constant_pool) {
        this.constant_pool = constant_pool;
    }

    public U2 getAccess_flag() {
        return access_flag;
    }

    public void setAccess_flag(U2 access_flag) {
        this.access_flag = access_flag;
    }

    public U2 getThis_class() {
        return this_class;
    }

    public void setThis_class(U2 this_class) {
        this.this_class = this_class;
    }

    public U2 getSuper_class() {
        return super_class;
    }

    public void setSuper_class(U2 super_class) {
        this.super_class = super_class;
    }

    public U2 getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(U2 interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public U2[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(U2[] interfaces) {
        this.interfaces = interfaces;
    }

    public U2 getFields_count() {
        return fields_count;
    }

    public void setFields_count(U2 fields_count) {
        this.fields_count = fields_count;
    }

    public FieldInfo[] getFields() {
        return fields;
    }

    public void setFields(FieldInfo[] fields) {
        this.fields = fields;
    }

    public U2 getMethod_count() {
        return method_count;
    }

    public void setMethod_count(U2 method_count) {
        this.method_count = method_count;
    }

    public MethodInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInfo[] methods) {
        this.methods = methods;
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
