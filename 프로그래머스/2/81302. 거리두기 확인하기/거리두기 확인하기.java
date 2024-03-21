import java.util.*;

class Solution {
    private Queue<int[]> que = new LinkedList<>();
    private final int[][] direction = {{1,0},{-1,0}, {0, 1}, {0, -1}};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            char[][] map = new char[5][5];
            for (int j = 0; j < 5; j++) {
                map[j] = places[i][j].toCharArray();
            }
            answer[i] = isKeepDistance(map) ? 1 : 0;
            que.clear();
        }

        return answer;
    }

    public boolean isKeepDistance(char[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    if (!isSafe(map, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSafe(char[][] map, int r, int c) {
        boolean[][] visited = new boolean[5][5];
        que.clear();
        que.offer(new int[]{r, c, 0});
        visited[r][c] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curK = cur[2];

            if (curK >= 2) {
                continue;
            }

            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (nextR >= 0 && nextR < 5 && nextC >= 0 && nextC < 5 && !visited[nextR][nextC]) {
                    if (map[nextR][nextC] == 'P') {
                        return false;
                    } else if (map[nextR][nextC] == 'O') {
                        que.offer(new int[]{nextR, nextC, curK + 1});
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
        return true;
    }
}
