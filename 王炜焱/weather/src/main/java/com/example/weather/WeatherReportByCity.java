package com.example.weather;

import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Calendar;

/**
 * 根据城市名/id查询天气
 * @author silk
 *
 */
public class WeatherReportByCity {
    /**
     * 根据城市名获取
     * @param cityName
     * @return
     */
    public static String excute(String cityName){
        String url=//此处以返回json格式数据示例,所以format=2,以根据城市名称为例,cityName传入中文
                "http://v.juhe.cn/weather/index?cityname="+cityName+"&key=60491dbf8aa906f77921372ad0aa7d0f";
        return PureNetUtil.get(url);//通过工具类获取返回数据
    }
    /**
     * 获取返回数据中的一个属性示例,此处以获取今日温度为例
     * "temperature": "8℃~20℃"     今日温度
     * @param args
     * @return 
     */
    public static String GetTodayTemperatureByCity(String city) {
        String result=excute(city);
        if(result!=null){
            JSONObject obj=JSONObject.fromObject(result);
            /*获取返回状态码*/
            result=obj.getString("resultcode");
            /*如果状态码是200说明返回数据成功*/
            if(result!=null&&result.equals("200")){
                result=obj.getString("result");
                //此时result中数据有多个key,可以对其key进行遍历,得到对个属性
                obj=JSONObject.fromObject(result);
                //今日温度对应的key是today
                result=obj.getString("today");
                obj=JSONObject.fromObject(result);
                //今日温度对应当key是temperature
                result=obj.getString("temperature");
                return result;
            }
        }
        return result;
    }
    
    public static int getMonthLastDay(int year, int month)  
    {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    
    public static void WriteStringToFile(String filePath) {
        try {
        	GetCityList list = new GetCityList();
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            int day =1;
            int month = 7,year =2017,day_total;
            day_total = WeatherReportByCity.getMonthLastDay(year, month);
            String dataF =null;
            for(int k=5;k<=6;k++){
            	String pro = "14";
            	pro = pro +k;
            	System.out.println(pro);
	            for(day=1;day<day_total;day++){
	            	dataF = "";
	            	String date = year +"-"+ month +"-" +day;
	            	//System.out.println(date);
	            	
	            	
	                String data = list.weather(pro, date);
	//                System.out.println("--" +data);
	            	data = data.substring(27,data.length()-1);
	            	data = data.replace("}", "");
	            	data = data.replace("\"", "");
	            	String str[] = data.split(",");
	            	
	            	for(String st:str){
//	            		System.out.println("st:"+st);
//	            		System.out.println("index:"+(st.indexOf(":")+1) +"  length:" +(st.length()-1));
	            		dataF +=st.substring((st.indexOf(":")+1),(st.length())) +" "; 
	            	}
	//            	System.out.println(data);
	                ps.append(dataF+"\r\n");// 在已有的基础上添加字符串
	            }
            }
            month++;
            day_total = WeatherReportByCity.getMonthLastDay(year, month); 

            for(int k=5;k<=6;k++){
            	String pro = "14";
            	pro = pro +k;
	            for(day=1;day<day_total;day++){
	            	dataF = "";
	            	String date = year +"-"+ month +"-" +day;
	            	//System.out.println(date);
	            	
	            	
	                String data = list.weather(pro, date);
	//                System.out.println("--" +data);
	            	data = data.substring(27,data.length()-1);
	            	data = data.replace("}", "");
	            	data = data.replace("\"", "");
	            	String str[] = data.split(",");
	            	
	            	for(String st:str){
	            		dataF +=st.substring((st.indexOf(":")+1),(st.length())) +" ";
	            	}
	//            	System.out.println(data);
	                ps.append(dataF+"\r\n");// 在已有的基础上添加字符串
	            }
            }

            System.out.println("success");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {

//    	int i = 2;
    	String url = "F:/sts/data.txt";

    	String data1 ="abc";
    	WeatherReportByCity.WriteStringToFile(url);
    	//System.out.println();
        
    	
        
    }
}