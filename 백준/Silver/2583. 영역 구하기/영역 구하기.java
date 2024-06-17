import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int m;
    private static int n;
    private static int k;
    private static int[][] map;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        Collections.sort(list);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            answer.append(list.get(i)).append(" ");
        }
        System.out.println(list.size());
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int r1 = Integer.parseInt(tokenizer.nextToken());
            int c1 = Integer.parseInt(tokenizer.nextToken());
            int r2 = Integer.parseInt(tokenizer.nextToken());
            int c2 = Integer.parseInt(tokenizer.nextToken());
            for (int j = r1; j < r2; j++) {
                for (int l = c1; l < c2; l++) {
                    map[j][l]++;
                }
            }
        }
    }

    private static void findAnswer() {
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j, visited);
                }
            }
        }
    }

    private static void bfs(int i, int j, boolean[][] visited) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {i, j});
        visited[i][j] = true;
        int count = 1;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] d : direction) {
                int nr = curR + d[0];
                int nc = curC + d[1];
                if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    que.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    count++;
                }
            }
        }
        list.add(count);
    }

    private static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < m;
    }
}