import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static boolean flag;
    private static Queue<int[]> que;
    private static boolean[][] visited;
    private static final int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        if (flag) {
            System.out.println(0);
            return;
        }

        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            while (size -- > 0) {
                int[] cur = que.poll();
                for (int[] dir : direction) {
                    int nextR = cur[0] + dir[0];
                    int nextC = cur[1] + dir[1];
                    if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != -1) {
                        que.offer(new int[] {nextR, nextC});
                        visited[nextR][nextC] = true;
                        map[nextR][nextC] = 1;
                    }
                }
            }
            time++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(time - 1);
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        que = new LinkedList<>();
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) {
                    que.add(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        flag = que.size() == n * m;
    }
}