import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by kallurivenkatesh on 9/21/15.
 */
public class sort_map extends Mapper<Object, Text, Text, IntWritable> {
    private Text key_of_sort = new Text();
    private IntWritable val_of_sort = new IntWritable();

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        StringTokenizer strr = new StringTokenizer(value.toString());
        while(strr.hasMoreTokens())
        {
            int num = Integer.parseInt(strr.nextToken());
            key_of_sort.set(num + "");
            val_of_sort.set(num);
            context.write(key_of_sort,val_of_sort);
        }
    }
}
