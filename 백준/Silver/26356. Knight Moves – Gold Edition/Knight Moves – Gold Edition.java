import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;
    private static int n;
    private static int r1, c1, r2, c2;
    private static int[][] direction = {{1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}};
    private static int min;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= testcase; i++) {
            init(input);
            findAnswer();
            answer.append("Case ").append("#").append(i + ":").append(" ").append(min).append("\n");
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void findAnswer() {
        visited[r1][c1] = true;
        bfs(r1, r2, c1, c2, visited);
    }

    private static void bfs(int r1, int r2, int c1, int c2, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r1, c1, 0});
        visited[r1][c1] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCost = cur[2];
            if (curR == r2 && curC == c2) {
                min = Math.min(min, curCost);
            }
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    q.offer(new int[]{nextR, nextC, curCost + 1});
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 1 <= r && r <= n && 1 <= c && c <= n;
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        r1 = Integer.parseInt(tokenizer.nextToken());
        c1 = Integer.parseInt(tokenizer.nextToken());
        r2 = Integer.parseInt(tokenizer.nextToken());
        c2 = Integer.parseInt(tokenizer.nextToken());
        min = Integer.MAX_VALUE;
        visited = new boolean[n + 1][n + 1];
    }
}