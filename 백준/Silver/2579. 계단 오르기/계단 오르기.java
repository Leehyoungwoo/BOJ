import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] nums;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dp[n]);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        dp = new int[n + 1];
        if (n >= 1) {
            dp[1] = nums[1];
        }
        if (n >= 2) {
            dp[2] = nums[1] + nums[2];
        }
        if (n >= 3) {
            dp[3] = Math.max(nums[1] + nums[3], nums[2] + nums[3]);
        }
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i - 1] + nums[i]);
        }
    }
}