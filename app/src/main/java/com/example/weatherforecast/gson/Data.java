package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    public String date;
    @SerializedName("wea")
    public String weather;
    public String wea_img;
    public String air;
    public String air_level;
    public String air_tips;
    public String tem;
    public String tem1;
    public String tem2;
    @SerializedName("index")
    public List<Suggestion> suggestions;
}
/*
"data": [
        {
            "day": "3日（今天）",
            "date": "2020-08-03",
            "week": "星期一",
            "wea": "阴转雷阵雨",
            "wea_img": "lei",
            "air": 52,
            "humidity": 95,
            "air_level": "良",
            "air_tips": "空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！",
            "alarm": {
                "alarm_type": "",
                "alarm_level": "",
                "alarm_content": ""
            },
            "tem1": "34℃",
            "tem2": "26℃",
            "tem": "29℃",
            "win": [
                "南风",
                "南风"
            ],
            "win_speed": "3-4级转<3级",
            "hours": [
                {
                    "day": "03日08时",
                    "wea": "阴",
                    "tem": "29℃",
                    "win": "南风",
                    "win_speed": "3-4级"
                },
                {
                    "day": "03日11时",
                    "wea": "阴",
                    "tem": "32℃",
                    "win": "南风",
                    "win_speed": "3-4级"
                },
                {
                    "day": "03日14时",
                    "wea": "阴",
                    "tem": "33℃",
                    "win": "南风",
                    "win_speed": "3-4级"
                },
                {
                    "day": "03日17时",
                    "wea": "多云",
                    "tem": "32℃",
                    "win": "南风",
                    "win_speed": "3-4级"
                },
                {
                    "day": "03日20时",
                    "wea": "阴",
                    "tem": "30℃",
                    "win": "南风",
                    "win_speed": "3-4级"
                },
                {
                    "day": "03日23时",
                    "wea": "雷阵雨",
                    "tem": "27℃",
                    "win": "南风",
                    "win_speed": "<3级"
                },
                {
                    "day": "04日02时",
                    "wea": "雷阵雨",
                    "tem": "27℃",
                    "win": "南风",
                    "win_speed": "<3级"
                },
                {
                    "day": "04日05时",
                    "wea": "多云",
                    "tem": "27℃",
                    "win": "南风",
                    "win_speed": "<3级"
                }
            ],
            "index": [
                {
                    "title": "中国人民保险<br>中暑指数",
                    "level": "极易中暑",
                    "desc": "暑气灼人，户外工作要避免长时间在阳光下曝晒。"
                },
                {
                    "title": "</em><em></em><em></em><em>",
                    "level": null,
                    "desc": "天热风大，可选择低强度运动。"
                },
                {
                    "title": "健臻·血糖指数",
                    "level": "易波动",
                    "desc": "血糖易波动，注意监测。"
                },
                {
                    "title": "穿衣指数",
                    "level": "炎热",
                    "desc": "建议穿短衫、短裤等清凉夏季服装。"
                },
                {
                    "title": "洗车指数",
                    "level": "不宜",
                    "desc": "有雨，雨水和泥水会弄脏爱车。"
                },
                {
                    "title": "紫外线指数",
                    "level": "最弱",
                    "desc": "辐射弱，涂擦SPF8-12防晒护肤品。"
                }
            ]
 */