import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static long[] date;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        date = new long[n];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            date[i] = Long.parseLong(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        long answer = k + 1;
        for (int i = 1; i < n; i++) {
            long gap = date[i] - date[i - 1];
            answer+=Math.min(gap, (long)k + 1);
        }

        System.out.println(answer);
    }
}