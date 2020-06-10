package com.example.weatherforecast.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();       //新建客户实体
        Request request = new Request.Builder().url(address).build();//发送请求
        client.newCall(request).enqueue(callback);  //新建子线程，在子线程中进行UI操作，并将最终结果回调到callback中
    }
}
