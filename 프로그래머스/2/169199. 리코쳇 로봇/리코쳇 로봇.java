import java.util.*;

class Solution {
    
    private final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int n, m;

    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();

        boolean[][] visited = new boolean[n][m];
        int startR = -1, startC = -1;

        // 시작 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curTurn = cur[2];

            // 목표 도달 시 바로 반환
            if (board[curR].charAt(curC) == 'G') {
                return curTurn;
            }

            // 4방향 리코쳇 이동
            for (int[] dir : direction) {
                int nextR = curR;
                int nextC = curC;

                // 장애물이나 범위 밖까지 미끄러지기
                while (isInRange(nextR + dir[0], nextC + dir[1]) &&
                        board[nextR + dir[0]].charAt(nextC + dir[1]) != 'D') {
                    nextR += dir[0];
                    nextC += dir[1];
                }

                if (!visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[] {nextR, nextC, curTurn + 1});
                }
            }
        }

        return -1; 
    }

    private boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
