package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;
//当前天气
/*
"now":{
    "tmp":"29",
    "cond":{
        "txt":"阵雨"
    }
}
 */
public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public State state;

    public class State {
        @SerializedName("txt")
        public String info;//天气情况，例如阵雨
    }
}
