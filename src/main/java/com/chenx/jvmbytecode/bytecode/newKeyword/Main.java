package com.chenx.jvmbytecode.bytecode.newKeyword;

public class Main {
    public static void main(String[] args) {
        /*
         0 new #2 <com/chenx/jvmbytecode/bytecode/newKeyword/UserService>
         3 dup
         4 invokespecial #3 <com/chenx/jvmbytecode/bytecode/newKeyword/UserService.<init> : ()V>
         7 astore_1
         8 aload_1
         9 invokevirtual #4 <com/chenx/jvmbytecode/bytecode/newKeyword/UserService.getUser : ()Lcom/chenx/jvmbytecode/bytecode/newKeyword/User;>
        12 astore_2
        13 aload_2
        14 invokevirtual #5 <com/chenx/jvmbytecode/bytecode/newKeyword/User.getName : ()Ljava/lang/String;>
        17 astore_3
        18 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
        21 aload_3
        22 invokevirtual #7 <java/io/PrintStream.println : (Ljava/lang/String;)V>
        25 return
         */
        UserService userService = new UserService();
        User user = userService.getUser();
        String name = user.getName();
        System.out.println(name);
    }
}
