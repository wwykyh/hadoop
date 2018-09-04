package com.example.weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
 
/**
 * 获取城市列表
 * 全国天气预报接口调用JAVA示例
 *     dtype     string    N    返回数据格式：json或xml,默认json    
 *     key        string     Y    你申请的key    
 * @author silk
 *
 */
public class GetCityList {
    /**
     * 调用获取城市列表接口,返回所有数据
     * @return 返回接口数据
     */
    public static String provinces(){
        String url="http://v.juhe.cn/historyWeather/province?key=d701d7956e3405ab60d4f5dc17c792c0";//接口URL
        //PureNetUtil是一个封装了get和post方法获取网络请求数据的工具类
        return PureNetUtil.get(url);//使用get方法
    }
    /**
     * 调用接口返回数据后,解析数据,根据输入城市名得到对应ID
     * @param cityName 城市名称
     * @return 返回对应ID
     */
    public static String citys(String province) {
    	String url= "http://v.juhe.cn/historyWeather/citys?province_id="+province+"&key=d701d7956e3405ab60d4f5dc17c792c0";
    	return PureNetUtil.get(url);//使用get方法
    	
    }
    
    public static String weather(String city, String wdate){
    	String url= "http://v.juhe.cn/historyWeather/weather?city_id="+city+"&key=d701d7956e3405ab60d4f5dc17c792c0&weather_date="+wdate;
    	return PureNetUtil.get(url);//使用get方法
    	
    }
   
}
