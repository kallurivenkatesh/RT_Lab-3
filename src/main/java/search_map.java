import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by kallurivenkatesh on 9/21/15.
 */

    public class search_map extends Mapper<Object,Text, Text, Text> {

        private String splitpart;
        private Text outputkeyname = new Text();
        private static int numberoftheline = 0;

        public void setup(Context context) throws InterruptedException, IOException {
            super.setup(context);
            splitpart = ((FileSplit) context.getInputSplit()).getPath().toString();
        }

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            StringTokenizer st = new StringTokenizer(value.toString());
            numberoftheline++;
            while (st.hasMoreTokens()) {
                outputkeyname.set(splitpart);
                context.write(outputkeyname, new Text(numberoftheline + "," + st.nextToken()));
            }
        }
    }

