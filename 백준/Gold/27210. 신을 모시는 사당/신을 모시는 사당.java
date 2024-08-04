import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] nums;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int cur = nums[i] == 1 ? -1 : 1;
            if (i == 0) {
                dp[i] = cur;
            } else {
                dp[i] = Math.max(dp[i - 1] + cur, cur);
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        dp = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = nums[i] == 1 ? 1 : -1;
            if (i == 0) {
                dp[i] = cur;
            } else {
                dp[i] = Math.max(dp[i - 1] + cur, cur);
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}