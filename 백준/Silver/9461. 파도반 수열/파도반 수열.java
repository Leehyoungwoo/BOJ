import java.io.*;
import java.util.*;

public class Main {

    private static int tc;
    private static int n;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int j = 6; j <= 100; j++) {
            dp[j] = dp[j - 1] + dp[j - 5];
        }
        for (int i = 0; i < tc; i++) {
            n = Integer.parseInt(input.readLine());
            answer.append(dp[n]).append("\n");
        }
        System.out.println(answer);
    }
}