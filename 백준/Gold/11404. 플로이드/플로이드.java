import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            if (c < dist[a][b]) {
                dist[a][b] = c;
            }
        }
    }

    private static void findAnswer() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) {
                        dist[i][j] = nd;
                    }
                }
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    answer.append("0").append(" ");
                } else {
                    answer.append(dist[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}