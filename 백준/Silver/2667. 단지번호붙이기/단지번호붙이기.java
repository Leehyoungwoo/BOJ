import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] map;
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int total;
    private static List<Integer> count = new ArrayList<>();
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(total);
        Collections.sort(count);
        for (int i = 0; i < count.size(); i++) {
            System.out.println(count.get(i));
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        visited = new boolean[n][n];
    }

    private static void findAnswer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    caculateVilige(i, j, 1);
                    total++;
                }
            }
        }
    }

    private static void caculateVilige(int curR, int curC, int cnt) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{curR, curC});
        visited[curR][curC] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] dir : direction) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    cnt++;
                    que.add(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
        count.add(cnt);
    }

    private static boolean isInRange(int nextR, int nextC) {
        return 0 <= nextR && nextR < n && 0 <= nextC && nextC < n;
    }
}