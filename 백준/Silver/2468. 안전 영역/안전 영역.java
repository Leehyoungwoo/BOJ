import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Set<Integer> set = new HashSet<>();
    private static List<Integer> list;
    private static int n;
    private static int[][] map;
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int max = 1;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(max);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = num;
                set.add(num);
            }
        }
        list = new ArrayList<>(set);
        Collections.sort(list);
        list.add(0);
    }

    private static void findAnswer() {
        for (Integer height : set) {
            boolean[][] visited = new boolean[n][n];
            int area = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > height && !visited[i][j]) {
                        caculate(visited, height, i, j);
                        area++;
                    }
                }
            }
            max = Math.max(area, max);
        }
    }

    private static void caculate(boolean[][] visited, Integer height, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for(int[] dir : direction) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] > height) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}