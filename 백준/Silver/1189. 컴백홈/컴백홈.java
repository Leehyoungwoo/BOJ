import java.util.*;
import java.io.*;

public class Main {

    private static int r, c, k;
    private static char[][] map;
    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        boolean[][] visited = new boolean[r][c];
        visited[r - 1][0] = true;
        int answer = findWayCount(r - 1, 0, 1, visited);
        System.out.println(answer);
    }

    private static int findWayCount(int x, int y, int way, boolean[][] visited) {
        if (x == 0 && y == c - 1) {
            return way == k ? 1 : 0;
        }
        int count = 0;
        for (int[] d : direction) {
            int nextR = x + d[0];
            int nextC = y + d[1];
            if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == '.') {
                visited[nextR][nextC] = true;
                count += findWayCount(nextR, nextC, way + 1, visited);
                visited[nextR][nextC] = false;
            }
        }

        return count;
    }

    private static boolean isInRange(int nextR, int nextC) {
        return 0 <= nextR && nextR < r && 0 <= nextC && nextC < c;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        r = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = input.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }
}