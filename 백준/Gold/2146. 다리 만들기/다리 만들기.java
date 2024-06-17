import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int[][] map;
    private static boolean[][] visited;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        int idx = 2; // 섬을 구분하기 위한 숫자 (2부터 시작)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    markIsland(i, j, idx);
                    idx++;
                }
            }
        }
    }

    private static void markIsland(int i, int j, int idx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        map[i][j] = idx;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    queue.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    map[nextR][nextC] = idx;
                }
            }
        }
    }

    private static void findAnswer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 1) {
                    bfs(i, j, map[i][j]);
                }
            }
        }
    }

    private static void bfs(int startR, int startC, int islandIdx) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] localVisited = new boolean[n][n];
        queue.offer(new int[]{startR, startC, 0});
        localVisited[startR][startC] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDist = cur[2];
            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];
                if (isInRange(nextR, nextC) && !localVisited[nextR][nextC]) {
                    localVisited[nextR][nextC] = true;
                    if (map[nextR][nextC] == 0) {
                        queue.offer(new int[]{nextR, nextC, curDist + 1});
                    } else if (map[nextR][nextC] != islandIdx) {
                        answer = Math.min(answer, curDist);
                        return;
                    }
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}