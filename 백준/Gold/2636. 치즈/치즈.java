import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] map;
    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];

        int cheeseCount = 0;
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) cheeseCount++;
            }
        }

        int roundCount = 0;
        int beforeCount = 0;

        while (cheeseCount > 0) {
            boolean[][] outside = markOutsideAir();

            Queue<int[]> melt = new LinkedList<>();
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (map[r][c] == 1) {
                        for (int[] d : direction) {
                            int nr = r + d[0], nc = c + d[1];
                            if (isValid(nr, nc) && outside[nr][nc]) {
                                melt.offer(new int[]{r, c});
                                break; 
                            }
                        }
                    }
                }
            }

            beforeCount = melt.size();          
            while (!melt.isEmpty()) {
                int[] cur = melt.poll();
                if (map[cur[0]][cur[1]] == 1) { 
                    map[cur[0]][cur[1]] = 0;
                    cheeseCount--;
                }
            }

            roundCount++;
        }

        System.out.println(roundCount);
        System.out.println(beforeCount);
    }

    private static boolean[][] markOutsideAir() {
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> dq = new ArrayDeque<>();

        dq.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1];

            if (map[r][c] != 0) continue;

            for (int[] d : direction) {
                int nr = r + d[0], nc = c + d[1];
                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    dq.offer(new int[]{nr, nc});
                }
            }
        }
        return visited;
    }

    private static boolean isValid(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }
}