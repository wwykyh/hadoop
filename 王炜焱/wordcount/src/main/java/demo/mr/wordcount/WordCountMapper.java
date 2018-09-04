package demo.mr.wordcount;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text , Text, Text> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = line.split(" ");
		String name =words[1];
		String date =words[2];
		String tmp = words[5];
		String tmp_n = words[6];
		String input =StringUtils.substringBeforeLast(date,"-"); 
		String inttmp = StringUtils.substringBefore(tmp, "℃");
		String inttmp_n =StringUtils.substringBefore(tmp_n, "℃");
		for(String word: words) {
			context.write(new Text(name +"--" + input), new Text(inttmp+"-"+inttmp_n));
			
		}
		
	}
	
	

}
