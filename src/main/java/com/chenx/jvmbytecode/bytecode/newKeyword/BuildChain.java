package com.chenx.jvmbytecode.bytecode.newKeyword;

/**
 * 测试链式调用会不会将每次返回的结果都存入操作数栈
 *  0 new #2 <com/chenx/jvmbytecode/bytecode/newKeyword/Builder>
 *  3 dup
 *  4 invokespecial #3 <com/chenx/jvmbytecode/bytecode/newKeyword/Builder.<init> : ()V>
 *  7 ldc #4 <A>
 *  9 invokevirtual #5 <com/chenx/jvmbytecode/bytecode/newKeyword/Builder.setA : (Ljava/lang/String;)Lcom/chenx/jvmbytecode/bytecode/newKeyword/Builder;>
 * 12 ldc #6 <B>
 * 14 invokevirtual #7 <com/chenx/jvmbytecode/bytecode/newKeyword/Builder.setB : (Ljava/lang/String;)Lcom/chenx/jvmbytecode/bytecode/newKeyword/Builder;>
 * 17 ldc #8 <C>
 * 19 invokevirtual #9 <com/chenx/jvmbytecode/bytecode/newKeyword/Builder.setC : (Ljava/lang/String;)Lcom/chenx/jvmbytecode/bytecode/newKeyword/Builder;>
 * 22 astore_1
 * 23 return
 *
 * 可以看到只有一个astore_1，说明链式调用Builder对象的setXX方法不会将每次返回的结果存入操作数栈，
 * 而只存储最后一次setXX方法返回的Builder对象的引用，所以构造者模式可以减少字节码指令
 */
public class BuildChain {
    public static void main(String[] args) {
        Builder builder = new Builder().setA("A")
                .setB("B")
                .setC("C");
    }
}

/**
 * 简单链式
 */
class Builder{

    private String a;
    private String b;
    private String c;

    public Builder setA(String a) {
        this.a = a;
        return this;
    }

    public Builder setB(String b) {
        this.b = b;
        return this;
    }

    public Builder setC(String c) {
        this.c = c;
        return this;
    }

}
