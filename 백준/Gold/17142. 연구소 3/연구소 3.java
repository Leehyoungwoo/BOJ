import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static List<int[]> virus;
    private static int[] comb;
    private static boolean flag;
    private static int minTime = Integer.MAX_VALUE;
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][n];
        virus = new ArrayList<>();
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
        comb = new int[m];
        // virus에 들어있는 거 중 m개 선택
        // 바이러스가 퍼지는 최소 시간 구하기
        // 모든 빈칸에 바이러스가 퍼지지 않으면 -1
        makeComb(0, 0);
        if (!flag) {
            System.out.println(-1);
            return;
        }

        System.out.println(minTime);
    }

    private static void makeComb(int idx, int start) {
        if (idx == m) {
            int time = findTime();
            if (minTime > time) {
                minTime = time;
            }

            if (minTime != Integer.MAX_VALUE) {
                flag = true;
            }
            return;
        }

        if (start == virus.size()) {
            return;
        }

        comb[idx] = start;
        makeComb(idx + 1, start + 1);
        comb[idx] = -1;
        makeComb(idx, start + 1);
    }

    private static int findTime() {
        int max = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> que = new LinkedList<>();
        for (int i : comb) {
            int[] v = virus.get(i);
            visited[v[0]][v[1]] = true;
            que.offer(new int[]{v[0], v[1], 0});
        }
        while (!que.isEmpty()) {
            int[] curVirus = que.poll();
            int curR = curVirus[0];
            int curC = curVirus[1];
            int curTime =curVirus[2];
            if (max < curTime && map[curR][curC] != 2) {
                max = curTime;
            }
            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 1) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC, curTime + 1});
                }

//                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 2) {
//                    visited[nextR][nextC] = true;
//                    que.offer(new int[]{nextR, nextC, curTime});
//                }
            }
        }

        // bfs가 끝난 후 모든 곳에 퍼졌는지 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return max;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
