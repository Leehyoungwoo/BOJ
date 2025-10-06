import java.io.*;
import java.util.*;

public class Main {

    private static int w;
    private static int h;
    private static int[][] map;
    private static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuffer answer = new StringBuffer();
        while(!(line = input.readLine()).equals("0 0")) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            w = Integer.parseInt(tokenizer.nextToken());
            h = Integer.parseInt(tokenizer.nextToken());
            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            // --------------------- FIND ANSWER --------------------------
            boolean[][] visited = new boolean[h][w];
            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(!visited[i][j] && map[i][j] == 1) {
                          bfs(visited, i, j);
                          count++;
                    }
                }
            }

            answer.append(count).append("\n");
        }

        System.out.println(answer);
    }

    private static void bfs(boolean[][] visited, int r, int c) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC});
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }
}