package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
{
    "cityid": "101010100",
    "update_time": "2020-07-30 14:55:32",
    "city": "北京",
    "cityEn": "beijing",
    "country": "中国",
    "countryEn": "China",
    "data": [
        {
            "day": "30日（今天）",
            "date": "2020-07-30",
            "week": "星期四",
            "wea": "多云",
            "wea_img": "yun",
 */
public class Weather {
    @SerializedName("cityid")
    public String weatherId;
    public String update_time;
    public String city;
    @SerializedName("data")
    public List<Data> dataList;
}
