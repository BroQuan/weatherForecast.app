package com.example.weatherforecast.util;

import android.text.TextUtils;

import com.example.weatherforecast.database.City;
import com.example.weatherforecast.database.County;
import com.example.weatherforecast.database.Province;
import com.example.weatherforecast.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //解析JSON数据：各个地区数据+天气数据


    //解析和处理服务器返回的省级数据
    /*
    [{"id":1,"name":"北京"},
     {"id":2,"name":"上海"},
     ……
    ]
     */

    public static boolean handleProvinceResponse(String response) throws JSONException {
        if (!TextUtils.isEmpty(response)) {         //响应判空操作
            JSONArray allProvinces = new JSONArray(response);
            for (int i = 0; i < allProvinces.length(); ++i) {
                JSONObject provinceObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                province.save();
            }
            return true;
        }
        return false;
    }
    //解析和处理服务器返回的市级数据
    /*
    [{"id":113,"name":"南京"},
    {"id":114,"name":"无锡"},
    ……
    ]
     */
    public static boolean handleCityResponse(String response, int provinceId) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allCities = new JSONArray(response);
            for (int i = 0; i < allCities.length(); ++i) {
                JSONObject cityObject = allCities.getJSONObject(i);
                City city = new City();
                city.setCityName(cityObject.getString("name"));
                city.setCityCode(cityObject.getInt("id"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        }
        return false;
    }
    //解析和处理服务器返回的县级数据

    /*
    [{"id":937,"name":"苏州","weather_id":"CN101190401"},
    {"id":938,"name":"常熟","weather_id":"CN101190402"},
    ……
    ]
     */
    public static boolean handleCountyResponse(String response, int cityId) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allCounties = new JSONArray(response);
            for (int i = 0; i < allCounties.length(); ++i) {
                JSONObject countyObject = allCounties.getJSONObject(i);
                County county = new County();
                county.setCountyName(countyObject.getString("name"));
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }
    //将返回的JSON数据解析成Weather实体类
    /*
    {
    "status": "ok",
    "basic": {},
    "aqi": {},
    "now": {},
    "suggestion": {},
    "daily_forecast": []
}
     */
    public static Weather handleWeatherResponse(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
        String weatherContent = jsonArray.getJSONObject(0).toString();
        return new Gson().fromJson(weatherContent, Weather.class);
    }
}
