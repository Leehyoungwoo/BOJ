import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] num;
    private static long[] prefix;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        num = new int[n];
        prefix = new long[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
        prefix[0] = num[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + num[i];
        }
        long[] remainderCount = new long[m];

        for (int i = 0; i < n; i++) {
            int remainder = (int) (prefix[i] % m);
            if (remainder < 0) {
                remainder += m;
            }
            remainderCount[remainder]++;
        }

        long count = remainderCount[0];
        for (int i = 0; i < m; i++) {
            if (remainderCount[i] > 1) {
                count += (remainderCount[i] * (remainderCount[i] - 1)) / 2;
            }
        }
        System.out.println(count);
    }
}