package com.jm.newvista.util;

import com.jm.newvista.util.sun.misc.BASE64Decoder;
import com.jm.newvista.util.sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {

    /**
     * 将byte数组以Base64方式编码为字符串
     *
     * @param bytes 待编码的byte数组
     * @return 编码后的字符串
     */
    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 将以Base64方式编码的字符串解码为byte数组
     *
     * @param encodeStr 待解码的字符串
     * @return 解码后的byte数组
     * @throws IOException
     */
    public static byte[] decode(String encodeStr) {
        byte[] bt = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            bt = decoder.decodeBuffer(encodeStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bt;
    }

    /**
     * 将两个byte数组连接起来后，返回连接后的Byte数组
     *
     * @param front 拼接后在前面的数组
     * @param after 拼接后在后面的数组
     * @return 拼接后的数组
     */
    public static byte[] connectBytes(byte[] front, byte[] after) {
        byte[] result = new byte[front.length + after.length];
        System.arraycopy(front, 0, result, 0, after.length);
        System.arraycopy(after, 0, result, front.length, after.length);
        return result;
    }

    /**
     * Read image from file system, and encode it to Base64 string
     *
     * @param imgUrl e.g. D:\\jsontest\\abc.jpg
     * @return
     * @throws IOException
     */
    public static String encodeImage(String imgUrl) throws IOException {
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }

//    public static void main(String[] args) {
//        String str;
//        try {
//            str = encodeImage("E:\\yunifang_img\\1.jpg");
//            System.out.println(str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}