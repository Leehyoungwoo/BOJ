import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int n = routes.length;
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));
        int count = 0;
        int lastCamera = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (routes[i][0] > lastCamera) {
                count++;
                lastCamera = routes[i][1];
            } 
        }
        
        return count;
    }
}