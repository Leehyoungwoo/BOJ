import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (canPlace(rocks, n, mid, distance)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    private boolean canPlace(int[] rocks, int n, int minDist, int distance) {
        int removed = 0;
        int lastPosition = 0;
        
        for (int rock : rocks) {
            if (rock - lastPosition < minDist) {
                removed++;
            } else {
                lastPosition = rock;
            }
        }
        
        if (distance - lastPosition < minDist) {
            removed++;
        }
        
        return removed <= n;
    }
}