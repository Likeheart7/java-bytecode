package com.chenx.jvmbytecode.bytecode.loop;

/**
 * 循环的字节码实现
 *  0 bipush 10
 *  2 istore_1
 *  3 iload_1
 *  4 ifle 20 (+16)     // 判断 <=，成立才能跳过goto语句
 *  7 getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;>
 * 10 iload_1
 * 11 iinc 1 by -1
 * 14 invokevirtual #3 <java/io/PrintStream.println : (I)V>
 * 17 goto 3 (-14)      // 跳回 3
 * 20 return
 */
public class LoopBytecode {
    public static void main(String[] args) {
        int cnt = 10;
        while (cnt > 0) {
            System.out.println(cnt--);
        }
    }
}
