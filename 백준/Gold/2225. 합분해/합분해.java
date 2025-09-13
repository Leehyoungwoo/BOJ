import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int k;
    private static int[][] dp;
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        dp = new int[n + 1][k + 1];
    }

    private static void findAnswer() {
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[j][i] == 0) continue;
                for (int l = 0; j + l <= n; l++) {
                    dp[j + l][i + 1] = ((dp[j + l][i + 1] + dp[j][i]) % MOD);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}