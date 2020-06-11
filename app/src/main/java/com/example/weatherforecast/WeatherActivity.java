package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecast.gson.Forecast;
import com.example.weatherforecast.gson.Weather;
import com.example.weatherforecast.service.AutoUpdateService;
import com.example.weatherforecast.util.HttpUtil;
import com.example.weatherforecast.util.Utility;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ImageView backgroundImage;
    public SwipeRefreshLayout swipeRefreshLayout;
    public DrawerLayout drawerLayout;
    private String mWeaterId;
    private Button changeCounty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherLayout = findViewById(R.id.weather_layout);
        titleCity = findViewById(R.id.title_city);
        titleUpdateTime = findViewById(R.id.title_update_time);
        degreeText = findViewById(R.id.degree_text);
        weatherInfoText = findViewById(R.id.weather_info_text);
        forecastLayout = findViewById(R.id.forecast_layout);
        aqiText = findViewById(R.id.aqi_text);
        pm25Text = findViewById(R.id.pm25_text);
        comfortText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.car_wash_text);
        sportText = findViewById(R.id.sport_text);
        backgroundImage = findViewById(R.id.back_image);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.bule);
        drawerLayout = findViewById(R.id.drawer_layout);
        changeCounty = findViewById(R.id.change_county);
        changeCounty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = sp.getString("weather", null);
        if (weatherString != null) {    // 有缓存，直接解析天气数据
            Weather weather = null;
            try {
                Log.d("WeatherActivity", weatherString);
                weather = Utility.handleWeatherResponse(weatherString);
                mWeaterId = weather.basic.weatherId;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            showWeatherInfo(weather);
        } else {
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mWeaterId);
            }
        });
    }

    //根据天气id请求天气信息
    public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" +
                weatherId + "&key=74c2ddda4da844c6809f5c093d25d8ff";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败",
                                Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather;
                try {
                    weather = Utility.handleWeatherResponse(responseText);   //将json数据转换成Weather对象，再将当前线程切换到主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (weather != null && "ok".equals(weather.status)) {
                                SharedPreferences.Editor editor = PreferenceManager.
                                        getDefaultSharedPreferences(WeatherActivity.this).edit();
                                editor.putString("weather", responseText);
                                editor.apply();
                                mWeaterId = weather.basic.weatherId;
                                showWeatherInfo(weather);
                            } else {
                                Toast.makeText(WeatherActivity.this, "获取天气失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.state.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        //各种背景图片
        switch (weatherInfo) {
            case "晴":
                backgroundImage.setBackgroundResource(R.drawable.sunny);
                break;
            case "多云":
            case "少云":
            case "晴间多云":
                backgroundImage.setBackgroundResource(R.drawable.cloudy);
                break;
            case "阴":
                backgroundImage.setBackgroundResource(R.drawable.overcast);
                break;
            case "有风":
            case "微风":
            case "和风":
            case "清风":
            case "强风/劲风":
                backgroundImage.setBackgroundResource(R.drawable.windy);
                break;
            case "疾风":
            case "大风":
            case "烈风":
            case "风暴":
            case "狂爆风":
            case "龙卷风":
            case "热带风暴":
                backgroundImage.setBackgroundResource(R.drawable.tornado);
                break;
            case "雨":
            case "小雨":
            case "阵雨":
            case "毛毛雨/细雨":
            case "小到中雨":
                backgroundImage.setBackgroundResource(R.drawable.light_rain);
                break;
            case "中到大雨":
            case "中雨":
            case "强阵雨":
            case "雷阵雨":
            case "强雷阵雨":
            case "雷阵雨伴有冰雹":
                backgroundImage.setBackgroundResource(R.drawable.shower_rain);
                break;
            case "大雨":
            case "极端降雨":
            case "暴雨":
            case "大暴雨":
            case "特大暴雨":
            case "冻雨":
            case "大到暴雨":
            case "暴雨到大暴雨":
            case "大暴雨到特大暴雨":
                backgroundImage.setBackgroundResource(R.drawable.rainy);
                break;
            case "雪":
            case "小雪":
            case "中雪":
            case "雨雪天气":
            case "阵雨夹雪":
            case "阵雪":
            case "小到中雪":
            case "雨夹雪":
                backgroundImage.setBackgroundResource(R.drawable.light_snow);
                break;
            case "大雪":
            case "暴雪":
            case "中到大雪":
            case "大到暴雪":
                backgroundImage.setBackgroundResource(R.drawable.snowy);
                break;
            case "薄雾":
            case "雾":
            case "霾":
            case "扬沙":
            case "浮尘":
            case "沙尘暴":
            case "强沙尘暴":
            case "浓雾":
            case "强浓雾":
            case "中度霾":
            case "重度霾":
            case "严重霾":
            case "大雾":
            case "特强浓雾":
                backgroundImage.setBackgroundResource(R.drawable.fog);
                break;
            default:
                backgroundImage.setBackgroundResource(R.drawable.others);
                break;
        }
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = view.findViewById(R.id.date_text);
            TextView infoText = view.findViewById(R.id.info_text);
            TextView maxText = view.findViewById(R.id.max_text);
            TextView minText = view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.state.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }
        aqiText.setText(weather.aqi.city.aqi);
        pm25Text.setText(weather.aqi.city.pm25);
        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        comfortText.setText(comfort);
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        carWashText.setText(carWash);
        String sport = "运动建议：" + weather.suggestion.sport.info;
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);  //后台自动更新天气服务
        startService(intent);
    }
}