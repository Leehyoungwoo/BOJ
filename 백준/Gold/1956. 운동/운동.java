import java.io.*;
import java.util.*;

public class Main {

    private static int v, e;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        dist = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            dist[a][b] = c;
        }
    }

    private static void findAnswer() {
        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 1; j <= v; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            min = Math.min(min, dist[i][i]);
        }


        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}