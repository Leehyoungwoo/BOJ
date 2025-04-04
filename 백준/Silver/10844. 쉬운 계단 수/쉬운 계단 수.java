import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        long[][] dp = new long[n+ 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int length = 2; length <= n; length++) {
            for (int digit = 0; digit <= 9; digit++) {
                if (digit > 0) {
                    dp[length][digit] += dp[length - 1][digit - 1];
                }
                if (digit < 9) {
                    dp[length][digit] += dp[length - 1][digit + 1];
                }
                dp[length][digit] %= MOD;
            }
        }

        long result = 0;
        for (int digit = 0; digit <= 9; digit++) {
            result = (result + dp[n][digit]) % MOD;
        }

        System.out.println(result);
    }
}
