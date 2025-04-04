import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int testcase;
    private static int k, n;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        answer = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            k = Integer.parseInt(input.readLine());
            n = Integer.parseInt(input.readLine());
            findAnswer();
        }

        System.out.println(answer);
    }

    private static void findAnswer() {
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= k; i++) {
            dp[i][1] = 1;
        }

        for (int floor = 1; floor <= k; floor++) {
            for (int room = 2; room <= n; room++) {
                dp[floor][room] = dp[floor][room - 1] + dp[floor - 1][room];
            }
        }

        answer.append(dp[k][n]).append("\n");
    }
}
