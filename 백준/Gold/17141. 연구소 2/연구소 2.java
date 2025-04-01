import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static List<int[]> virus;
    private static int[] comb;
    private static int minTime = Integer.MAX_VALUE;
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int impossibleCount;
    private static int combCount;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        virus = new ArrayList<>();
        map = new int[n][n];
        comb = new int[m];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        // dfs로 list size에서 m개의 조합 구하기
        makeComb(0, 0);
        if (impossibleCount == combCount) {
            System.out.println(-1);
            return;
        }
        System.out.println(minTime);
    }

    private static void makeComb(int start, int idx) {
        if (idx == m) {
            // 조합을 구할 때 마다  bfs로 최대 시간 구하기
            combCount++;
            int finalTime = bfs();
            minTime = Math.min(finalTime, minTime);
            return;
        }

        if (start == virus.size()) {
            return;
        }

        comb[idx] = start;
        makeComb(start + 1, idx + 1);
        comb[idx] = -1;
        makeComb(start + 1, idx);
    }

    private static int bfs() {
        int maxTime = 0;
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < comb.length; i++) {
            int[] cur = virus.get(comb[i]);
            que.offer(new int[] {cur[0], cur[1], 0});
            visited[cur[0]][cur[1]] = true;
        }

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curTime = cur[2];
            maxTime = Math.max(maxTime, curTime);
            for (int[] direction : direction) {
                int nextR = curR + direction[0];
                int nextC = curC + direction[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 1) {
                    que.offer(new int[] {nextR, nextC, curTime + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }

        if (!isAllVirus(visited)) {
            impossibleCount++;
            return Integer.MAX_VALUE;
        }

        return maxTime;
    }

    private static boolean isAllVirus(boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1 && !visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}