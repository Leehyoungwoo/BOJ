import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        coins = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(input.readLine());
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int coin : coins) {
            for (int i = coin; i <= k; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println(dp[k] != Integer.MAX_VALUE ? dp[k] : -1);
    }
}