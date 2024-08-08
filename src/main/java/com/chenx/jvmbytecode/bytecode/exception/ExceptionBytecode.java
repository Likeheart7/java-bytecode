package com.chenx.jvmbytecode.bytecode.exception;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 探索try catch对应字节码
 */
public class ExceptionBytecode {
    public static void main(String[] args) {

    }

    /**
     *  0 bipush 10
     *  2 istore_1
     *  3 iconst_0
     *  4 istore_2
     *  5 iload_1
     *  6 iload_2
     *  7 idiv
     *  8 ireturn
     *  9 astore_1
     * 10 iconst_m1
     * 11 ireturn
     *
     * 异常表
     * from    to  target   type
     *   0     8     9      Class java/lang/ArithmeticException
     *
     *   关注异常表。意思是在执行偏移量为[0, 8)的字节码的过程中如果抛出ArithmeticException，
     *   将执行偏移量从9开始的字节码。
     */
    public int tryCatch() {
        try {
            int n = 10;
            int m = 0;
            return n / m;
        } catch (ArithmeticException e) {
            return -1;
        }
    }

    /**
     * 加上finally块
     *
     *  0 bipush 10
     *  2 istore_1
     *  3 iconst_0
     *  4 istore_2
     *  5 iload_1
     *  6 iload_2
     *  7 idiv
     *  8 istore_3
     *  9 getstatic #3 <java/lang/System.out : Ljava/io/PrintStream;>
     * 12 ldc #4 <enter finally block...>
     * 14 invokevirtual #5 <java/io/PrintStream.println : (Ljava/lang/String;)V>
     * 17 iload_3
     * 18 ireturn
     * 19 astore_1
     * 20 iconst_m1
     * 21 istore_2
     * 22 getstatic #3 <java/lang/System.out : Ljava/io/PrintStream;>
     * 25 ldc #4 <enter finally block...>
     * 27 invokevirtual #5 <java/io/PrintStream.println : (Ljava/lang/String;)V>
     * 30 iload_2
     * 31 ireturn
     * 32 astore 4
     * 34 getstatic #3 <java/lang/System.out : Ljava/io/PrintStream;>
     * 37 ldc #4 <enter finally block...>
     * 39 invokevirtual #5 <java/io/PrintStream.println : (Ljava/lang/String;)V>
     * 42 aload 4
     * 44 athrow
     *
     * 异常表
     *   from       to  target      type
     *   0          9    19         Class java/lang/ArithmeticException
     *   0          9    32         any
     *   19         22    32        any
     *   32         34    32        any
     *
         其中[0,9)代表try块的逻辑；
         [9,14]是将finally块逻辑；是复制过来的，用于在没有一场的情况下执行finally块的代码。
         [17,18]是return；
         [19,32)是catch块逻辑；其中也包括了复制过去的finally块逻辑。
         [32, 34)是finally执行前将异常先存到局部变量表，用于后续还原到操作数栈顶；
         [34, 39]是finally块逻辑。
         也就是说，
         1. 如果try中正常执行，会在return命令前，将复制过去的finally块的逻辑执行。
         2. 如果try抛出了ArithmeticException，跳到偏移量19开始的catch块逻辑。
         a. 如果catch块没问题，会执行复制过去的finally块逻辑。
         b. 如果catch块抛出异常，跳到32，保存异常，执行finally逻辑。
         3. 如果try块中抛出的不是ArithmeticException，会跳到32，先将异常保存，用于后续还原至操作数栈顶，然后执行finally块逻辑。
         4. 如果[32, 34)保存异常到局部变量表时有问题，会跳回到32重试。
     */
    public int tryCatchFinally() {
        try {
            int n = 10;
            int m = 0;
            return n / m;
        } catch (ArithmeticException e) {
            return -1;
        } finally {
            System.out.println("enter finally block...");
        }
    }

    /**
     *  创建FileInputStream，局部变量表索引为1的Slot存储FileInputStream对象
     *  0 new #6 <java/io/FileInputStream>
     *  3 dup
     *  4 ldc #7 </target/xxx>
     *  6 invokespecial #8 <java/io/FileInputStream.<init> : (Ljava/lang/String;)V>
     *  局部变量表索引为1的Slot存储FileInputStream对象
     *  9 astore_1
     * 10 aconst_null
     * 11 astore_2
     * 12 aload_1
     * 13 ifnull 40 (+27)
     * 16 aload_2
     * 17 ifnull 36 (+19)
     * 如果局部变量in不为null，且try块抛出的异常不为null，调用close方法
     * 20 aload_1
     * 21 invokevirtual #9 <java/io/FileInputStream.close : ()V>
     * 24 goto 40 (+16)
     * 调用addSuppressed方法将close方法抛出的异常添加到try代码块抛出的异常
     * 27 astore_3
     * 28 aload_2
     * 29 aload_3
     * 30 invokevirtual #11 <java/lang/Throwable.addSuppressed : (Ljava/lang/Throwable;)V>
     * 33 goto 40 (+7)
     * 调用close方法
     * 36 aload_1
     * 37 invokevirtual #9 <java/io/FileInputStream.close : ()V>
     * 40 goto 44 (+4)
     * 43 astore_1
     * 44 return
     *
     * 异常表
     *  from    to  target type
     *             20    24    27   Class java/lang/Throwable
     *              0    40    43   Class java/lang/Exception
     */
    public void tryWithResource() {
        try(FileInputStream inputStream = new FileInputStream("/target/xxx")){
            // pass
        } catch (Exception e){
            // pass
        }
    }
}
