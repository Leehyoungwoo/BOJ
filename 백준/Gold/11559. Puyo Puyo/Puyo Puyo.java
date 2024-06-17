import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static char[][] map;
    private static boolean[][] toErase;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = input.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    private static void findAnswer() {
        int count = 0;
        while (canPuyo()) {
            puyo();
            dropDown();
            count++;
        }
        System.out.println(count);
    }

    private static void dropDown() {
        for (int col = 0; col < 6; col++) {
            int emptyRow = 11;
            for (int row = 11; row >= 0; row--) {
                if (map[row][col] != '.') {
                    char temp = map[row][col];
                    map[row][col] = '.';
                    map[emptyRow][col] = temp;
                    emptyRow--;
                }
            }
        }
    }

    private static void puyo() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (toErase[i][j]) {
                    map[i][j] = '.';
                }
            }
        }
    }

    private static boolean canPuyo() {
        boolean flag = false;
        toErase = new boolean[12][6];
        boolean[][] visited = new boolean[12][6];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != '.' && !visited[i][j]) {
                    int count = bfs(i, j, visited, map[i][j]);
                    if (count >= 4) {
                        flag = true;
                        markForErasure(i, j, map[i][j]);
                    }
                }
            }
        }
        return flag;
    }

    private static void markForErasure(int i, int j, char target) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i, j});
        toErase[i][j] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] d : direction) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (isInRange(nr, nc) && !toErase[nr][nc] && map[nr][nc] == target) {
                    que.add(new int[]{nr, nc});
                    toErase[nr][nc] = true;
                }
            }
        }
    }

    private static int bfs(int i, int j, boolean[][] visited, char target) {
        int count = 1;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i, j});
        visited[i][j] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] d : direction) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == target) {
                    que.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < 12 && 0 <= nc && nc < 6;
    }
}