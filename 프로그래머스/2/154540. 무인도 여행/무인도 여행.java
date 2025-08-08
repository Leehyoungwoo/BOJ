import java.util.*;

class Solution {
    
    private final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0;
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    sum += bfs(i, j, maps, n, m, visited);
                }
                
                if (sum != 0) {
                    answer.add(sum);
                }
            }
        }
        
        Collections.sort(answer);
        if (answer.size() == 0) {
            return new int[] {-1};
        }
        int[] arr = new int[answer.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
    
    private int bfs(int r, int c, String[] maps, int n, int m, boolean[][] visited) {
        Queue<int[]> que = new LinkedList<>();
        int sum = 0;
        que.offer(new int[] {r, c});
        visited[r][c] = true;
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            sum+=maps[curR].charAt(curC) - '0';
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC, n, m) && !visited[nextR][nextC] && maps[nextR].charAt(nextC) != 'X') {
                    que.offer(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            } 
        }
        
        return sum;
    }
    
    private boolean isInRange(int r, int c, int n, int m) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}