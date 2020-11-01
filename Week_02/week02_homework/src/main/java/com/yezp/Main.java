package com.yezp;


import com.alibaba.fastjson.JSONObject;
import com.yezp.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

//        JSONObject json;
        String url = "http://localhost:8801";
        Map<String, String> paraMap = new HashMap<String, String>();

        try {
            String result = HttpClientUtil.sendPost(url, paraMap);
            if (result == null || result.length() == 0) {
                System.out.println("result is empty.");
            }

//            json = JSONObject.parseObject(result);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}