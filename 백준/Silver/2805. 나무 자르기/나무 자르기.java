import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static long m;
    private static long[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Long.parseLong(tokenizer.nextToken());
        trees = new long[n];
        tokenizer = new StringTokenizer(input.readLine());
        long maxLen = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(tokenizer.nextToken());
            maxLen = Math.max(maxLen, trees[i]);
        }
        long left = 0;
        long right = maxLen;
        long result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (long tree : trees) {
                if (tree > mid) {
                    sum += (tree - mid);
                }
            }
            if (sum >= m) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }
}