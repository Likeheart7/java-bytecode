package com.chenx.jvmbytecode.type.constant;

import java.nio.ByteBuffer;

public interface ConstantInfoHandler {
    /**
     * 常量解析方法
     */
    void read(ByteBuffer codeBuf) throws Exception;
}
