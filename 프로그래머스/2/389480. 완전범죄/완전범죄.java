import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;
        for (int[] in : info) {
            int a = in[0];
            int b = in[1];
            
            boolean[][] next = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!dp[i][j]) {
                        continue;
                    }
                    
                    if (a + i < n) {
                        next[a + i][j] = true;
                    }
                    
                    if (b + j < m) {
                        next[i][b + j] = true;
                    }
                }
            }
            dp = next;
        }
        
        for(int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b]) {
                   return a;
                }                
            }
        }
        
        return -1;  
    }
}
