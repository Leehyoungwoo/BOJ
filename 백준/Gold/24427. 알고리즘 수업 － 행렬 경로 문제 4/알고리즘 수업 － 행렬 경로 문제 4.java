import java.io.*;
import java.util.*;

public class Main {

    private static final int NEG = Integer.MIN_VALUE / 4;
    private static int n;
    private static int[][] nums;
    private static int p;
    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        p = Integer.parseInt(input.readLine());
        set = new HashSet<>();
        for (int i = 0; i < p; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a =  Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            set.add(a * n + b);
        }
    }

    private static void findAnswer() {
        int[][][] dp = new int[n + 1][n + 1][2];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = dp[i][0][1] = NEG;
            dp[0][i][0] = dp[0][i][1] = NEG;
        }
        dp[1][1][0] = nums[0][0];
        dp[1][1][1] = NEG;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;

                int prev0 = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                int prev1 = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);

                if (set.contains(i * n + j)) {
                    dp[i][j][0] = NEG; // 더 이상 미방문 상태 유지 불가
                    int fromAny = Math.max(prev0, prev1);
                    dp[i][j][1] = (fromAny == NEG) ? NEG : fromAny + nums[i - 1][j - 1];
                } else {
                    dp[i][j][0] = (prev0 == NEG) ? NEG : prev0 + nums[i - 1][j - 1];
                    dp[i][j][1] = (prev1 == NEG) ? NEG : prev1 + nums[i - 1][j - 1];
                }
            }
        }

        System.out.println(dp[n][n][1]);
    }
}