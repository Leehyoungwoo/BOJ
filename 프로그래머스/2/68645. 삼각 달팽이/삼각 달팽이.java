import java.util.*;

class Solution {
    
    private int[][] map;
    private final int[][] direction = {{1, 0}, {0, 1}, {-1, -1}};
    
    public int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        map = new int[n][n];
        fillMap(n);
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[idx++] = map[i][j];
            }
        }
        return answer;
    }
    
    private void fillMap(int n) {
        int r = 0;
        int c = 0;
        int num = 1;
        int d = 0;
        int maxNum = n * (n + 1) / 2;
        while(num <= maxNum) {
            map[r][c] = num++;
            int nextR = r + direction[d % 3][0];
            int nextC = c + direction[d % 3][1];
            if (!canGo(nextR, nextC, n)) {
                d++;
                nextR = r + direction[d % 3][0];
                nextC = c + direction[d % 3][1];
            } 
            r = nextR;
            c = nextC;
        }
    }

    private boolean canGo(int r, int c, int n) {
        return 0 <= r && r < n && 0 <= c && c < n 
            && map[r][c] == 0;
    }
}