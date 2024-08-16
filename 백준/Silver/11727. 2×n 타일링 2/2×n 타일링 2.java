import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] dp = new int[n + 1];
        int mod = 10007;

        dp[1] = 1;
        if (n > 1) {
            dp[2] = 3;
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % mod;
        }

        System.out.println(dp[n]);
    }
}