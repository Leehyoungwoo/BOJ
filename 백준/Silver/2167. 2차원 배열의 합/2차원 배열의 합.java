import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] prefix;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        prefix = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }
        k = Integer.parseInt(input.readLine());
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            int sum = prefix[x2][y2] - prefix[x2][y1 - 1] - prefix[x1 - 1][y2] + prefix[x1 - 1][y1 - 1];
            answer.append(sum).append("\n");
        }
        System.out.println(answer);
    }
}