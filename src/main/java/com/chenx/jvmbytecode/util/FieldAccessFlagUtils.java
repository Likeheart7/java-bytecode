package com.chenx.jvmbytecode.util;

import com.chenx.jvmbytecode.type.U2;

import java.util.HashMap;
import java.util.Map;

/**
 * 将字段的访问标志，access_flags转为字符串输出的工具类
 * 对应信息如下：
 *
 * 标志名	        十六进制值	说明
 * ACC_PUBLIC	    0x 00 01	声明为public
 * ACC_PRIVATE	    0x 00 02	声明为private
 * ACC_PROTECTED	0x 00 04	声明为protected
 * ACC_STATIC	    0x 00 08	声明为static
 * ACC_FINAL	    0x 00 10	声明为final
 * ACC_VOLATILE	    0x 00 40	声明为volatile
 * ACC_TRANSIENT	0x 00 80	声明为transient
 * ACC_SYNTHTIC	    0x 10 00	标志该字段由编译器产生，不在源码中
 * ACC_ENUM	        0x 40 00	声明为枚举类型的成员
 *
 *
 *
 */
public class FieldAccessFlagUtils {
    private static final Map<Integer, String> fieldAccessFlagMap = new HashMap<>();
    static {
        fieldAccessFlagMap.put(0x0001, "public");
        fieldAccessFlagMap.put(0x0002, "private");
        fieldAccessFlagMap.put(0x0004, "protected");
        fieldAccessFlagMap.put(0x0008, "static");
        fieldAccessFlagMap.put(0x0010, "final");
        fieldAccessFlagMap.put(0x0040, "volatile");
        fieldAccessFlagMap.put(0x0080, "transient");
        fieldAccessFlagMap.put(0x1000, "synthtic");
        fieldAccessFlagMap.put(0x4000, "enum");
    }

    public static String toFieldAccessFlagsString(U2 flag) {
        Integer flagValue = flag.toInt();
        StringBuilder flagBuilder = new StringBuilder();
        fieldAccessFlagMap.keySet().forEach(key -> {
            if ((key & flagValue) == key) {
                flagBuilder.append(fieldAccessFlagMap.get(key)).append(",");
            }
        });

        // 返回时，去除末尾的 ","
        return flagBuilder.length() > 0 && (flagBuilder.charAt(flagBuilder.length() - 1) == ',')
                ? flagBuilder.substring(0, flagBuilder.length() - 1)
                : flagBuilder.toString();
    }
}
