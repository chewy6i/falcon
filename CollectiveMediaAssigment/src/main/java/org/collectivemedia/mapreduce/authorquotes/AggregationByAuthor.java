package org.collectivemedia.mapreduce.authorquotes;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AggregationByAuthor {

	public static class CountMapper
				extends Mapper<Object, Text, Text, IntWritable> {
				
				private final static IntWritable one = new IntWritable(1);
				private Text word = new Text();
				
				public void map(Object key, Text value, Context context)
					throws IOException, InterruptedException {
					
					String strval = value.toString();
					
					//split the text into author and the quote using '/t'delimiter
					int k = strval.indexOf('\t');
					if (k < 0) {
						return;
					}

					
					String sauthor = strval.substring(0, k);
					word.set(sauthor);
					context.write(word, one);
				}
			}
			
			
			public static class CountReducer 
				extends Reducer<Text, IntWritable, Text, IntWritable> {

				private IntWritable result = new IntWritable();
				
				public void reduce(Text key, Iterable<IntWritable> values,
						Context context) throws IOException, InterruptedException {
					int sum = 0;
					for (IntWritable val: values) {
						sum += val.get();
					}
					
					result.set(sum);
					context.write(key,  result);
				}
			}
			
			/**
			 * @param args
			 */
			public void RunJob(String name, String input, String output) {
				Configuration conf = new Configuration();
				
				Job job1 = null;
				try {
					job1 = new Job(conf, name);
					job1.setJarByClass(AggregationByAuthor.class);
					job1.setMapperClass(CountMapper.class);
					job1.setCombinerClass(CountReducer.class);
					job1.setReducerClass(CountReducer.class);
					job1.setOutputKeyClass(Text.class);
					job1.setOutputValueClass(IntWritable.class);
					
					FileInputFormat.addInputPath(job1, new Path(input));
					FileOutputFormat.setOutputPath(job1, new Path(output));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					System.exit(job1.waitForCompletion(true) ? 0: 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	}
