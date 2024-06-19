import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();

        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= 1000000;
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= 1000000;
            }
        }

        System.out.println(dp[n]);
    }
}