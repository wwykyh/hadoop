package demo.mr.wordcount;

import java.io.IOException;


import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, Text , Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		
		Text str =null;
		int day_avetmp =0;
		int day_sumtmp =0;
		int night_avetmp =0;
		int night_sumtmp =0;
		int max_day_tmp = 0;
		int max_night_tem=0;
		int i=0;;
		for(Text value :  values) {
			
			String val = value.toString();
			String st[] = val.split("-");
			if(i==0){
				max_day_tmp=Integer.parseInt(st[0]);
				max_night_tem=Integer.parseInt(st[1]);
			}else{
				if(max_day_tmp<Integer.parseInt(st[0])){
					max_day_tmp =Integer.parseInt(st[0]);
				}
				if(max_night_tem<Integer.parseInt(st[1])){
					max_night_tem=Integer.parseInt(st[1]);
				}
			}
			
			day_sumtmp += Integer.parseInt(st[0]);
			night_sumtmp +=Integer.parseInt(st[1]);
			i++;
		}
		day_avetmp = day_sumtmp/i;
		night_avetmp = night_sumtmp/i;
		str =  new Text("白天平均气温: " +day_avetmp+"℃" +"    夜间平均气温: "+night_avetmp+"℃" +"   白天最高温度:"+max_day_tmp+"℃"+"   夜间最高温度:"+max_night_tem+"℃");
		context.write(key, new Text(str));
	}


}
