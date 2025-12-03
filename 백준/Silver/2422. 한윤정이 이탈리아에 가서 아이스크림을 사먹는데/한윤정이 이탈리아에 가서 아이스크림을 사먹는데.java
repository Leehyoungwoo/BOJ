import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int n, m;
    private static boolean[][] bad;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static int findAnswer() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (bad[i][j]) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (bad[i][k] || bad[k][j]) continue;
                    count++;
                }
            }
        }

        return count;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        bad = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            bad[a][b] = true;
            bad[b][a] = true;
        }
    }
}