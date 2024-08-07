package com.chenx.jvmbytecode.classfileparse.handler;

import com.chenx.jvmbytecode.classfileparse.type.ClassFile;
import com.chenx.jvmbytecode.classfileparse.type.U2;

import java.nio.ByteBuffer;

/**
 * 解析this和super引用。
 * Object类的super引用是0，其他都是ConstantClassInfo类型的索引。接口的superClass指向Object类的ConstantClassInfo常量
 * 由于到这里已经完成了常量池的解析，在解析获取到thisClass与superClass之后，可以根据thisClass的值到常量池中取得对应的ConstantClassInfo常量，
 * 再从取得的ConstantClassInfo常量中获取该常量的nameIndex的值，最后根据nameIndex再回到常量池中取得对应的ConstantUtf8Info常量，这样就能获取到具体的类名了。
 */
public class ThisAndSuperClassHandler implements BaseByteCodeHandler{

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 thisClass = new U2(codeBuf.get(), codeBuf.get());
        U2 superClass = new U2(codeBuf.get(), codeBuf.get());
        classFile.setThis_class(thisClass);
        classFile.setSuper_class(superClass);
    }
}
