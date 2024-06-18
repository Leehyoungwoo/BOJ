import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int m = Integer.parseInt(input.readLine());
        List<Integer> vipSeats = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            vipSeats.add(Integer.parseInt(input.readLine()));
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int result = 1;
        int previousVIP = 0;

        for (int vip : vipSeats) {
            int length = vip - previousVIP - 1;
            result *= dp[length];
            previousVIP = vip;
        }

        int lastSegmentLength = n - previousVIP;
        result *= dp[lastSegmentLength];

        System.out.println(result);
    }
}