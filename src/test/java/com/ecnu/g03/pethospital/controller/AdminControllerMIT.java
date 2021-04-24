package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminUpdateResponse;
import com.ecnu.g03.pethospital.testutils.TestUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/24 11:18
 */
public class AdminControllerMIT {

    static private CloseableHttpClient httpClient;
    static private Gson gson;

    @BeforeAll
    static void init() {
        httpClient = HttpClients.createDefault();
        gson = new Gson();
    }

    @AfterAll
    static void after() throws IOException {
        httpClient.close();
    }

    @ParameterizedTest
    @MethodSource("provideUpdatePasswordBySelfSource")
    public void testUpdatePasswordBySelf(String requestBody, ResponseStatus expectedStatus) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8081/admin/reset/password/byself";
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(requestBody, "utf-8");
        requestEntity.setContentEncoding("UTF-8");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        InputStream inputStream = response.getEntity().getContent();
        String result = TestUtils.inputStream2String(inputStream);
        System.out.print("result" + result);
        AdminUpdateResponse jsonResponse = gson.fromJson(result, AdminUpdateResponse.class);
        Assert.assertEquals(expectedStatus, jsonResponse.getStatus());
        httpClient.close();
    }

    static List<Arguments> provideUpdatePasswordBySelfSource() {
        return Arrays.asList(
                Arguments.of(
                        "{\"userName\": \"orange\", \"oldPassword\": \"1111\", \"newPassword\": \"11112Ab!\", \"confirmPassword\": \"11112Ab!\"}",
                        ResponseStatus.SUCCESS));

    }

}
