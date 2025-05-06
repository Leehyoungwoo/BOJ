import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int n, m;
    private static int[][] map;
    private static List<int[]> ice;
    private static boolean flag;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        ice = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] != 0) {
                    ice.add(new int[]{i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        // 리스트에 빙산을 담아두고 0이되면 제거하기? 빙산은 만개
        int time = 0;
        // 매 while문 순회에서 bfs를 통해서 덩어리가 몇개인지 체크
        while (isOneIce()) {
            int[] minus = new int[ice.size()];
            for (int i = 0; i < ice.size(); i++) {
                int oceanCount = 0;
                int[] cur = ice.get(i);
                for (int[] dir : direction) {
                    int nextR = cur[0] + dir[0];
                    int nextC = cur[1] + dir[1];
                    if (isInRange(nextR, nextC) && map[nextR][nextC] == 0) {
                        oceanCount++;
                    }
                    minus[i] = -oceanCount;
                }
            }

            List<int[]> removeAll = new ArrayList<>();
            for (int i = 0; i < minus.length; i++) {
                int[] cur = ice.get(i);
                int curR = cur[0];
                int curC = cur[1];
                if (map[curR][curC] + minus[i] > 0) {
                    map[curR][curC] += minus[i];
                } else {
                    map[curR][curC] = 0;
                    removeAll.add(cur);
                }
            }
            ice.removeAll(removeAll);
            time++;
        }
        if (flag) {
            System.out.println(0);
            return;
        }
        // while문에서 time을 증가시키다가 빙산이 두조각으로 나뉘면 break 후 타임문 출력
        System.out.println(time);
    }

    private static boolean isOneIce() {
        if (ice.isEmpty()) {
            flag = true;
            return false;
        }
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        que.offer(ice.get(0));
        visited[ice.get(0)[0]][ice.get(0)[1]] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int[] dir : direction) {
                int nextR = cur[0] + dir[0];
                int nextC = cur[1] + dir[1];
                if (isInRange(nextR, nextC) && map[nextR][nextC] != 0 && !visited[nextR][nextC]) {
                    que.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }

        for (int[] cur : ice) {
            if (map[cur[0]][cur[1]] != 0 && !visited[cur[0]][cur[1]]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}