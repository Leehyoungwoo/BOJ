import java.util.*;

class Solution {
    
    private char[][] board;
    private final int[][] direction = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
    private int n, m;
    private int[] lever;
    private int[] start;
    private int[] end;
    
    public int solution(String[] maps) {
        init(maps);
        // 출발점부터 레버까지의 최단 거리 가져오고
        // 레버부터 도착점까지의 최단 거리를 가져오면 됨
        int answer = 0;
        int first =findMin(start, lever, new boolean[n][m]);
        if (first == -1) {
            return -1;
        }
        
        int second =findMin(lever, end, new boolean[n][m]);
        if (second <= -1) {
            return -1;
        }
        
        return answer + first + second;
    }
    
    private int findMin(int[] s, int[] e, boolean[][] visited) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {s[0], s[1], 0});
        visited[s[0]][s[1]] = true;
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int sum = cur[2];
            if (curR == e[0] && curC == e[1]) {
                return sum;
            }
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && board[nextR][nextC] != 'X') {
                    que.offer(new int[] {nextR, nextC, sum + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }
        
        return -1;
    }
    
    private boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m; 
    }
    
    private void init(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = maps[i].charAt(j);
                if (board[i][j] == 'S') {
                    start = new int[] {i, j};
                }
                
                if (board[i][j] == 'L') {
                    lever = new int[] {i, j};
                }
                
                if (board[i][j] == 'E') {
                    end = new int[] {i, j};
                }
            }
        }
    }
}