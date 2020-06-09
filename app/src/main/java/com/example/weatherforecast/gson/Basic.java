package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    //由于json中的一些字段不适合直接作为Java字段来命名，所以使用@SerializedName("city")注解的方式将jason和java字段之间建立映射关系
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;
    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
