import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], 0);
        }
        
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = 1;
            graph[loser][winner] = -1;
        }
        
        for(int k = 1; k <=n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                }
            }
        }
        
        int cnt = 0;
        for(int i =1; i <= n; i++) {
            int result = 0;
            for(int j = 1; j <= n; j++) {
                if (graph[i][j] != 0) {
                    result++;
                }
            }
            if(result == n - 1) {
                cnt++;
            }
        }
        return cnt;
    }
}