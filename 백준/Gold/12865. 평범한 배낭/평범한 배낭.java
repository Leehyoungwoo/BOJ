import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static List<int[]> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            inputs.add(new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())});
        }
    }

    private static void findAnswer() {
        int[] dp = new int[k + 1];
        for (int[] cur : inputs) {
            int weight = cur[0];
            int value = cur[1];

            for (int i = k; i >= weight; i--) {
                dp[i] = Math.max(dp[i], dp[i - weight] + value);
            }
        }

        System.out.println(dp[k]);
    }
}