package com.example.sellersystem.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 优雅的短UUID:
 * JAVA生成UUID的方式虽然已经很通用了，但是依然有一个小缺点，占用的空间太大，
 * 所有表的ID都要占用32位的字符。所以我自己设计了一个短UUID，原理就是生成一个8位的62进制数，
 * 将所有的数字、大小写字母全部用上（数据库UUID是16进制，只用了数字和6个字母
 */
@Component
public class UUIDUtil {
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String getShortUuid() {
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            builder.append(chars[x % 0x3E]);
        }
        return builder.toString();

    }

}
