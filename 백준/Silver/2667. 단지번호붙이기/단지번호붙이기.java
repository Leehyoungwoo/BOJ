import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] map;
    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static Map<Integer, List<int[]>> villiges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        int mark = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, ++mark, visited);
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append(villiges.size()).append("\n");
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, List<int[]>> entry : villiges.entrySet()) {
            list.add(villiges.get(entry.getKey()).size());
        }
        Collections.sort(list);
        for(Integer num : list) {
            answer.append(num).append("\n");
        }
        System.out.println(answer);
    }

    private static void bfs(int r, int c,int mark, boolean[][] visited) {
        List<int[]> list = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{r, c});
        visited[r][c] = true;
        list.add(new int[]{r, c});
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int newR = curR + dir[0];
                int newC = curC + dir[1];
                if (isInRange(newR, newC) && !visited[newR][newC] && map[newR][newC] == 1) {
                    que.offer(new int[] {newR, newC});
                    visited[newR][newC] = true;
                    list.add(new int[] {newR, newC});
                }
            }
        }
        villiges.put(mark, list);
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}