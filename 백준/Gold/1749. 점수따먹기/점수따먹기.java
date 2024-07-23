import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] prefix;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n + 1][m + 1];
        prefix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = map[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
    }

    private static void findAnswer() {
        int maxSum = Integer.MIN_VALUE;

        for (int r1 = 1; r1 <= n; r1++) {
            for (int r2 = r1; r2 <= n; r2++) {
                for (int c1 = 1; c1 <= m; c1++) {
                    for (int c2 = c1; c2 <= m; c2++) {
                        int sum = prefix[r2][c2]
                                - (r1 > 1 ? prefix[r1 - 1][c2] : 0)
                                - (c1 > 1 ? prefix[r2][c1 - 1] : 0)
                                + (r1 > 1 && c1 > 1 ? prefix[r1 - 1][c1 - 1] : 0);

                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}