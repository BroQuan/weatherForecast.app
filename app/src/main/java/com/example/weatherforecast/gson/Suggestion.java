package com.example.weatherforecast.gson;

import com.google.gson.annotations.SerializedName;

/*出行建议
 "index": [
                {
                    "title": "中国人民保险<br>中暑指数",
                    "level": "极易中暑",
                    "desc": "酷热难当，要注意合理饮，运动前后尽量避免一次性大量饮水。"
                },
                {
                    "title": "</em><em></em><em></em><em>",
                    "level": null,
                    "desc": "天气闷热，坚持室内低强度运动。"
                },
                {
                    "title": "健臻·血糖指数",
                    "level": "易波动",
                    "desc": "血糖易波动，注意监测。"
                },
                {
                    "title": "穿衣指数",
                    "level": "热",
                    "desc": "适合穿T恤、短薄外套等夏季服装。"
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
public class Suggestion {//3-5有效
    public String title;
    public String level;
    public String desc;
}
