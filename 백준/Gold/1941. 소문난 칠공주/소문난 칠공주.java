import java.io.*;
import java.util.*;

public class Main {

    private static char[][] map = new char[5][5];
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int answer = 0;
    private static boolean[] selected = new boolean[25];

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String line = input.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    private static void findAnswer() {
        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int idx, int count) {
        if (count == 7) {
            List<Integer> picked = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                if (selected[i]) picked.add(i);
            }
            if (isConnected(picked)) answer++;
            return;
        }

        if (idx >= 25) return;

        selected[idx] = true;
        combination(idx + 1, count + 1);
        selected[idx] = false;
        combination(idx + 1, count);
    }

    private static boolean isConnected(List<Integer> picked) {
        boolean[] visited = new boolean[7];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        int connected = 1;
        int yCount = map[picked.get(0) / 5][picked.get(0) % 5] == 'Y' ? 1 : 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int r = picked.get(cur) / 5;
            int c = picked.get(cur) % 5;

            for (int[] dir : direction) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (!isInRange(nr, nc)) continue; 

                int nextIndex = nr * 5 + nc;

                for (int i = 0; i < 7; i++) {
                    if (!visited[i] && picked.get(i) == nextIndex) {
                        visited[i] = true;
                        queue.add(i);
                        connected++;
                        if (map[nr][nc] == 'Y') yCount++;
                    }
                }
            }
        }

        return connected == 7 && yCount <= 3;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < map.length && 0 <= c && c < map[0].length;
    }
}