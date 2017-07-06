package com.kangyonggan.app.dfjz.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.StringWriter;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
public class GsonUtil {

    /**
     * 格式化json
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String format(String data) throws Exception {
        Gson gson = new Gson();
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = gson.newJsonWriter(stringWriter);
        writer.setIndent("\t");

        JsonElement jsonElement = new JsonParser().parse(data);
        gson.toJson(jsonElement, writer);

        return stringWriter.toString();
    }

}
