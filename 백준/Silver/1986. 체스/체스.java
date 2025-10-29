import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int queenCnt;
    private static List<int[]> queens;
    private static int knightCount;
    private static List<int[]> knights;
    private static int pawnCount;
    private static List<int[]> pawns;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        queenCnt = Integer.parseInt(tokenizer.nextToken());
        queens = new LinkedList<>();
        for (int i = 0; i < queenCnt; i++) {
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            queens.add(new int[]{r, c});
        }

        tokenizer = new StringTokenizer(input.readLine());
        knights = new LinkedList<>();
        knightCount = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < knightCount; i++) {
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            knights.add(new int[]{r, c});
        }

        tokenizer = new StringTokenizer(input.readLine());
        pawnCount = Integer.parseInt(tokenizer.nextToken());
        pawns = new LinkedList<>();
        for (int i = 0; i < pawnCount; i++) {
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            pawns.add(new int[]{r, c});
        }
    }

    private static void findAnswer() {
        boolean[][] visited = new boolean[n][m];
        boolean[][] isPresent = new boolean[n][m];
        // 현위치 마킹
        marking(isPresent);
        int[][] direction = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        moving(queens, visited, direction, n, isPresent);
        direction = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {-1, 2}, {1, -2}, {1, 2}, {-1, -2}};
        moving(knights, visited, direction, 1, isPresent);

        int safeZone = findSafe(visited, isPresent);

        System.out.println(safeZone);
    }

    private static void moving(List<int[]> list, boolean[][] visited, int[][] direction, int range, boolean[][] isPresent) {
        Deque<int[]> deque = new ArrayDeque<>();
        for (int[] cur : list) {
            deque.offer(cur);
        }
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int curR = cur[0] - 1;
            int curC = cur[1] - 1;
            for (int[] dir : direction) {
                for (int i = 1; i <= range; i++) {
                    int nextR = curR + dir[0] * i;
                    int nextC = curC + dir[1] * i;
                    if (isInRange(nextR, nextC)) {
                        if (isPresent[nextR][nextC]) {
                            break;
                        }
                        visited[nextR][nextC] = true;
                    }
                }
            }

        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void marking(boolean[][] visited) {
        markCur(queens, visited);
        markCur(knights, visited);
        markCur(pawns, visited);
    }

    private static void markCur(List<int[]> list, boolean[][] visited) {
        for (int[] cur : list) {
            visited[cur[0] - 1][cur[1] - 1] = true;
        }
    }

    private static int findSafe(boolean[][] visited, boolean[][] isPresent) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && !isPresent[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}