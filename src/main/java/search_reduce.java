import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by kallurivenkatesh on 9/21/15.
 */
public class search_reduce extends Reducer<Text, Text, Text, IntWritable> {
    private static String tosearchword = "";
    public void setup(Context context) throws InterruptedException, IOException {
        tosearchword = context.getConfiguration().get(search_main.KEY_WORD);
    }
    public void reduce(Text keyIn, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        for (Text val : values) {
            String text = val.toString();
            if (text.contains(tosearchword)) {
                String[] contains = text.split(" , ");
                context.write(keyIn, new IntWritable(Integer.parseInt(contains[0])));
            }
        }
    }
}
