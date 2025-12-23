import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int answer = 0;
        int left = 1;
        int right = distance;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValidMid(distance, rocks, n, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isValidMid(int distance, int[] rocks, int n, int target) {
        int removed = 0;
        int prev = 0;
        for (int i = 0; i < rocks.length; i++) {
            int diff = rocks[i] - prev;
            if (diff >= target) {
                prev = rocks[i];
                continue;
            }
            removed++;
            
            if (removed > n) {
                return false;
            }
        }
        
        if (distance - prev < target) removed++;
        
        return removed <= n;
    }
}