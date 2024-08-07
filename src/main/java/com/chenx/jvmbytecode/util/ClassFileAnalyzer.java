package com.chenx.jvmbytecode.util;

import com.chenx.jvmbytecode.handler.*;
import com.chenx.jvmbytecode.type.ClassFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 调用解析器的类
 */
public class ClassFileAnalyzer {
    private final static List<BaseByteCodeHandler> BYTE_CODE_HANDLERS = new ArrayList<>();

    static {
        BYTE_CODE_HANDLERS.add(new MagicHandler());
        BYTE_CODE_HANDLERS.add(new VersionHandler());
        BYTE_CODE_HANDLERS.add(new ConstantPoolHandler()); // 常量池解析器
        BYTE_CODE_HANDLERS.add(new AccessFlagHandler()); // 访问修饰符解析器
        BYTE_CODE_HANDLERS.add(new ThisAndSuperClassHandler()); // this和super解析器
        BYTE_CODE_HANDLERS.add(new InterfaceHandler()); // 接口解析器
        BYTE_CODE_HANDLERS.add(new FieldHandler()); // 字段解析器
        BYTE_CODE_HANDLERS.add(new MethodHandler()); // 方法解析器
        BYTE_CODE_HANDLERS.add(new AttributeHandler()); // 属性表解析器

        // 根据每个解析器返回的排序信息，对List中的各个解析器排序。
        BYTE_CODE_HANDLERS.sort(Comparator.comparingInt(BaseByteCodeHandler::order));
    }

    /**
     * 逐个调用解析器，将class文件读取的字节缓存，解析成一个ClassFile对象
     * @param codeBuf   class文件对应的字节，ByteBuffer对象可以更好的控制读取
     * @return  解析出来的ClassFile对象
     */
    public static ClassFile analysis(ByteBuffer codeBuf) throws Exception {
        codeBuf.position(0  );
        ClassFile classFile = new ClassFile();
        for (BaseByteCodeHandler handler : BYTE_CODE_HANDLERS){
            handler.read(codeBuf, classFile);
        }
        System.out.println("class文件解析完成，剩余未解析字节数：" + codeBuf.remaining());  // 未0表示正常解析完成
        return classFile;
    }

    /**
     * 将文件读取后，转为ByteBuffer对象
     * @param classPath 文件路径
     */
    public static ByteBuffer readFile(String classPath) throws Exception {
        File file = new File(classPath);
        if (!file.exists()){
            throw new Exception("file not exists");
        }
        byte[] byteCodeBuf = new byte[4096];
        int length;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            length = inputStream.read(byteCodeBuf);
        }
        if (length < 1) {
            throw new Exception("not read byte code");
        }
//        将字节数组包装为ByteBuffer
        return ByteBuffer.wrap(byteCodeBuf, 0, length).asReadOnlyBuffer();
    }
}
