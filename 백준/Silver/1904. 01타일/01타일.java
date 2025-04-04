import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static final int MOD = 15_746;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
    }

    private static void findAnswer() {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        if (n == 1) {
            System.out.println(1);
            return;
        }
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i]  = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        System.out.println(dp[n]);
    }
}
