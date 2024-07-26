import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] remove = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        dp[0] = arr[0];
        remove[0] = 0;
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            remove[i] = Math.max(dp[i - 1], remove[i - 1] + arr[i]);
            max = Math.max(max, Math.max(dp[i], remove[i]));
        }
        System.out.println(max);
    }
}