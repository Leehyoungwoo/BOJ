import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static long[] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        arr = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        int start = 0;
        int end = start + m;
        long sum = Long.MIN_VALUE;
        while (end <= n) {
            if (prefix[end] - prefix[start] > sum) {
                sum = prefix[end] - prefix[start];
            } else if (prefix[end] - prefix[start] <= sum) {
                start++;
                end++;
            }
        }
        if (sum == Long.MIN_VALUE) {
            sum = 0;
        }

        System.out.println(sum);
    }
}