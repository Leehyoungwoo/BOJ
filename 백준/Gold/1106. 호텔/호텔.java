import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int c, n;
    private static int[] dp;
    private static int[][] num;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        c = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        dp = new int[c + 1];
        num = new int[n][2];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            num[i][0] = Integer.parseInt(tokenizer.nextToken());
            num[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        Arrays.fill(dp, 10000000);
        dp[0] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < n; j++) {
                int cost = num[j][0];
                int people = num[j][1];
                if (i + people < dp.length) {
                    dp[i + people] = Math.min(dp[i] + cost, dp[i + people]);
                } else {
                    min = Math.min(dp[i] + cost, min);
                }
            }
        }

        System.out.println(Math.min(min, dp[c]));
    }
}