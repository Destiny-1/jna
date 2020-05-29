package com.jna.resolver.Utils;

import com.sun.jna.Pointer;

import java.io.UnsupportedEncodingException;

public class FormatUtils {
    public static String returnStrFunc(Pointer pointer) {
        byte[] byteArray = pointer.getByteArray(0, 512);
        String mess = null;
        try {
            mess = new String(byteArray, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mess;
    }

    public static long Ip2Int(String strIp) {
        String[] ss = strIp.split("\\.");
        if (ss.length != 4) {
            return 0;
        }
        byte[] bytes = new byte[ss.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(ss[i]);
        }
        return byte2Int(bytes);
    }

    private static long byte2Int(byte[] bytes) {
        int n = bytes[0] & 0xFF;
        n |= ((bytes[1] << 8) & 0xFF00);
        n |= ((bytes[2] << 16) & 0xFF0000);
        n |= ((bytes[3] << 24) & 0xFF000000);
        return n & 0xFFFFFF;
    }
}
