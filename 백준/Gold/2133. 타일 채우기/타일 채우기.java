import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        dp = new int[n + 1];

        dp[0] = 1;
        if(n >= 2) dp[2] = 3;
        if(n >= 4) dp[4] = 11;
        for (int i = 6; i <= n; i++) {
            dp[i] = 4 * dp[i - 2] - dp[i - 4];
        }
        System.out.println(dp[n]);
    }
}