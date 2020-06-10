package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;

//气象预报
/*
"daily_forecast":[
    {
        "date":"2019-08-08",
        "cond":{
            "txt_d":"阵雨"
        },
        "tmp":{
            "max":"34",
            "min":"27"
        }
    },
    {
        "date":"2019-08-09",
        "cond":{
            "txt_d":"多云"
        },
        "tmp":{
            "max":"35",
            "min":"29"
        }
    },
    ...
}
daily_forecast中包含的是一个数组，数组中的每一项都代表着未来一天的天气信息。
我的方法是定义出单日天气的实体类，然后在声明实体类引用的时候使用List类型来进行声明。
 */

public class Forecast {
    public String date;
    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public State state;

    public class Temperature {
        public String max;
        public String min;
    }

    public class State {
        @SerializedName("txt_d")
        public String info;
    }
}
