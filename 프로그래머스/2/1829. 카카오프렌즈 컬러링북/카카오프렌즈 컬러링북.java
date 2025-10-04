import java.util.*;

class Solution {
    
    private int maxSizeOfOneArea = Integer.MIN_VALUE;
    private final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        // boolean형 필요 
        boolean[][] visited = new boolean[m][n];
        // 완전 탐색
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    bfs(i, j, visited, picture, picture[i][j]);
                }
            }
        }
        int[] answer = new int[2];
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
    
    private void bfs(int r, int c, boolean[][] visited, int[][] picture ,int target) {
        int count = 0;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {r, c});
        visited[r][c] = true;
        while (!que.isEmpty()) {
            count++;
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC +dir[1];
                if (isInRange(nextR, nextC, visited) && !visited[nextR][nextC] && picture[nextR][nextC] == target) {
                    que.offer(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
    }
    
    private boolean isInRange(int r, int c, boolean[][] visited) {
        return 0 <= r && r < visited.length && 0 <= c && c < visited[0].length;
    }
}