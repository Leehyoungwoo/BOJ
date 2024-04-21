import java.util.*;

class Solution {
    
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.sort(costs, (o1, o2) -> o1[2] -o2[2]);
                for(int i = 0; i < costs.length; i++) {
            if(find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }
        return answer;
    }
    
    private int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if(aP <= bP) {
            parent[bP] = aP;
            return;
        }
        
        parent[aP] = bP;
    }
}