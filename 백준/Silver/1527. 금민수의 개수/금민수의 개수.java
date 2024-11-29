import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int a, b;
    private static List<Long> list = new ArrayList<>();
    private static int count;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(count);
    }


    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
    }

    private static void findAnswer() {
        generateGoldNum(0);
        Collections.sort(list);
        for (long num : list) {
            if (a <= num && num <= b) {
                count++;
            }
        }
    }

    private static void generateGoldNum(long cur) {
        if (cur > 1_000_000_000) return;
        if (cur > 0) list.add(cur);  
        generateGoldNum(cur * 10 + 4);    
        generateGoldNum(cur * 10 + 7);    
    }
}