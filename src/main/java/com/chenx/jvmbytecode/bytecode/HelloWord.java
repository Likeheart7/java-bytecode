package com.chenx.jvmbytecode.bytecode;

public class HelloWord {
    /*
    将局部变量表的一个值赋给另一个值，必须经过操作数栈。所以说字节码指令集是基于栈的指令集
     */
    public static void main(String[] args) {
        System.out.println("hello bytecode");
    }

    public void local() {
        /*
        对应的字节码指令：
         0 bipush 10      向操作数栈压 10
         2 istore_1       将当前操作数栈顶的元素存储到局部变量表索引为1的Slot(第二个slot，第一个是this，非静态方法的局部变量表的下标0用于保存this引用)
         3 bipush 20      向操作数栈压 20
         5 istore_2       栈顶的赋给索引为2的slot
         6 iload_2        加载索引为2的slot的值到栈顶
         7 istore_3       将栈顶的赋给索引为3的slot
         8 iload_1        加载索引为1的slot的值到栈顶
         9 istore_2       将栈顶元素存到索引为 2的slot
        10 return
         */
        int a = 10, b = 20;
        int c = b;
        b = a;
    }
}
