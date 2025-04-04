import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static List<int[]> list;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int time = Integer.parseInt(tokenizer.nextToken());
            int earn = Integer.parseInt(tokenizer.nextToken());
            list.add(new int[]{time, earn});
        }
    }

    private static void findAnswer() {
        int[] dp = new int[n + 2];

        for (int i = 0; i < n; i++) {
            int[] cur = list.get(i);
            int time = cur[0];
            int money = cur[1];

            dp[i + 1] = Math.max(dp[i+ 1], dp[i]);
            if (time + i <= n) {
                dp[i + time] = Math.max(dp[i + time], dp[i] + money);
            }
        }

        System.out.println(dp[n]);
    }
}
