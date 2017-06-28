package com.kangyonggan.app.dfjz.common;

/**
 * @author kangyonggan
 * @since 6/28/17
 */
public class CharsetUtil {

    public static final String UTF8_TO_GBK = "1";
    public static final String GBK_TO_UTF8 = "2";
    public static final String STRING_TO_UNICODE = "3";
    public static final String UNICODE_TO_STRING = "4";

    public static String convert(String data, String operation) {
        if (UTF8_TO_GBK.equals(operation)) {
            return uf8ToGbk(data);
        } else if (GBK_TO_UTF8.equals(operation)) {
            return gbkToUtf8(data);
        } else if (STRING_TO_UNICODE.equals(operation)) {
            return stringToUnicode(data);
        } else if (UNICODE_TO_STRING.equals(operation)) {
            return unicodeToString(data);
        }
        return data;
    }

    public static String uf8ToGbk(String data) {
        return convertCharset(data, "UTF-8", "GBK");
    }

    public static String gbkToUtf8(String data) {
        return convertCharset(data, "GBK", "UTF-8");
    }

    public static String convertCharset(String data, String oldCharset, String newOldCharset) {
        try {
            return new String(data.getBytes(oldCharset), newOldCharset);
        } catch (Exception e) {
            return data;
        }
    }

    public static String stringToUnicode(String data) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < data.length(); i++) {
            sb.append("\\u" + Integer.toHexString(data.charAt(i)));
        }

        return sb.toString();
    }

    public static String unicodeToString(String data) {
        data = data.replaceAll("\\\\\\\\u", "\\\\u");
        String[] hex = data.split("\\\\u");
        if (hex.length == 1) {
            return data;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < hex.length; i++) {
            sb.append((char) Integer.parseInt(hex[i], 16));
        }

        return sb.toString();
    }
}
