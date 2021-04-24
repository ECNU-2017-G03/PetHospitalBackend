package com.ecnu.g03.pethospital.testutils;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shen Lei
 * @date 2021/4/24 23:44
 */
public class TestUtils {
    static public JSONArray inputStream2JSONArray(InputStream inputStream) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] b = new byte[4096];
        int n;
        while((n = inputStream.read(b)) != -1) {
            stringBuffer.append(new String(b, 0, n));
        }
        JSONArray jsonArray = new JSONArray(stringBuffer.toString());
        return jsonArray;
    }

    static public String inputStream2String(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] b = new byte[4096];
        int n;
        while((n = inputStream.read(b)) != -1) {
            stringBuffer.append(new String(b, 0, n));
        }
        return stringBuffer.toString();
    }
}
