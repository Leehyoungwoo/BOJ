import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        boolean[][] puddleMap = new boolean[n + 1][m + 1];
        for (int[] puddle : puddles) {
            puddleMap[puddle[1]][puddle[0]] = true;
        }
        
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        return dfs(1, 1, n, m, puddleMap, dp);
    }

    private int dfs(int x, int y, int n, int m, boolean[][] puddleMap, int[][] dp) {
        if (x == n && y == m) {
            return 1;
        }
        
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        
        int paths = 0;
        
        if (x + 1 <= n && !puddleMap[x + 1][y]) {
            paths += dfs(x + 1, y, n, m, puddleMap, dp);
            paths %= MOD;
        }
        
        if (y + 1 <= m && !puddleMap[x][y + 1]) {
            paths += dfs(x, y + 1, n, m, puddleMap, dp);
            paths %= MOD;
        }
        
        dp[x][y] = paths;
        
        return paths;
    }
}
