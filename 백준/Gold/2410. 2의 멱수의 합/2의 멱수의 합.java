import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int n;
    private static int[] dp;
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
    }

    private static void findAnswer() {
        dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i*=2) {
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % MOD;
            }
        }

        System.out.println(dp[n]);
    }
}