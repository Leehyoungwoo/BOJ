import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private final static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int m;
    private static int[][] map;
    private static int notTomatos;
    private static int answer = Integer.MAX_VALUE;
    private static Queue<int[]> que = new LinkedList<>();
    private static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) {
                    que.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
                if (map[i][j] == -1) {
                    notTomatos++;
                }
            }
        }
    }

    private static void findAnswer() {
        int sum = que.size();
        sum = bfs(sum);
        if (sum != n * m - notTomatos) {
            System.out.println(-1);
            return;
        }
        if(answer == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }

    private static int bfs(int sum) {
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];
            int depth = cur[2];
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int newDepth = depth + 1;
                if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    sum++;
                    que.offer(new int[]{nr, nc, newDepth});
                    visited[nr][nc] = true;
                    if (sum == n * m - notTomatos) {
                        answer = Math.min(answer, depth + 1);
                    }
                }
            }
        }

        return sum;
    }

    private static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < m && 0 <= nc && nc < n;
    }
}