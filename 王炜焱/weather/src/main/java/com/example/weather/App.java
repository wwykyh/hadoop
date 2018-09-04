package com.example.weather;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
//    	String cityids= GetCityList.weather("146", "2018-08-01");
//    	System.out.println(cityids);
    	String str = "145 霞浦 2017-07-01 多云 多云 30℃ 25℃ 无持续风向 ≤3级 无持续风向 ≤3级 01 01 0 ";
		String[] words = str.split(" ");
		System.out.println(words[1]);
    	
    }
}
