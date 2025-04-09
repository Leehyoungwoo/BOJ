import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int n, m, k;
    private static List<int[]> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        inputs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int score = Integer.parseInt(tokenizer.nextToken());
            // 오로지 동쪽 -> 서쪽만
            if (to > from) {
                inputs.add(new int[]{from, to, score});
            }
        }
    }

    private static void findAnswer() {
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[1][1] = 0;
        inputs.sort(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < inputs.size(); i++) {
            int from = inputs.get(i)[0];
            int to = inputs.get(i)[1];
            int score = inputs.get(i)[2];
            // 갱신해야하는 경우
            for (int cnt = 1; cnt <= m - 1; cnt++) {
                // 방문한 적이 없는 도시인것
                if (dp[from][cnt] == Integer.MIN_VALUE) continue;
                dp[to][cnt + 1] = Math.max(dp[to][cnt + 1], dp[from][cnt] + score);
            }
        }

        int answer = 0;
        for (int cnt = 1; cnt <= m; cnt++) {
            answer = Math.max(answer, dp[n][cnt]);
        }

        System.out.println(answer);
    }
}
