import java.io.*;
import java.util.*;

public class Main {

    private static int[] dp = new int[1001];
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        n = Integer.parseInt(input.readLine());
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < 1001; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 10007;
        }

        System.out.println(dp[n]);
    }
}