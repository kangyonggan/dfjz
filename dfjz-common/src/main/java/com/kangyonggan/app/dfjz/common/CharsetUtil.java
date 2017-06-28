package com.kangyonggan.app.dfjz.common;

/**
 * @author kangyonggan
 * @since 6/28/17
 */
public class CharsetUtil {

    private static final String UTF8 = "UTF-8";
    private static final String GBK = "GBK";
    private static final String IOS88591 = "IOS-8859-1";

    public static final String UTF8_TO_GBK = "1";
    public static final String UTF8_TO_ISO88591 = "2";
    public static final String GBK_TO_UTF8 = "3";
    public static final String GBK_TO_ISO88591 = "4";
    public static final String ISO88591_TO_UTF8 = "5";
    public static final String ISO88591_TO_GBK = "6";

    public static final String STRING_TO_UNICODE = "7";
    public static final String UNICODE_TO_STRING = "8";

    public static String convert(String data, String operation) {
        if (UTF8_TO_GBK.equals(operation)) {
            return convertCharset(data, UTF8, GBK);
        } else if (UTF8_TO_ISO88591.equals(operation)) {
            return convertCharset(data, UTF8, IOS88591);
        } else if (GBK_TO_UTF8.equals(operation)) {
            return convertCharset(data, GBK, UTF8);
        } else if (GBK_TO_ISO88591.equals(operation)) {
            return convertCharset(data, GBK, IOS88591);
        } else if (ISO88591_TO_UTF8.equals(operation)) {
            return convertCharset(data, IOS88591, UTF8);
        } else if (ISO88591_TO_GBK.equals(operation)) {
            return convertCharset(data, IOS88591, GBK);
        } else if (STRING_TO_UNICODE.equals(operation)) {
            return stringToUnicode(data);
        } else if (UNICODE_TO_STRING.equals(operation)) {
            return unicodeToString(data);
        }
        return data;
    }

    public static String convertCharset(String data, String oldCharset, String newCharset) {
        try {
            return new String(data.getBytes(oldCharset), newCharset);
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
        try {
            for (int i = 1; i < hex.length; i++) {
                sb.append((char) Integer.parseInt(hex[i], 16));
            }
        } catch (Exception e) {
            return data;
        }

        return sb.toString();
    }
}
