import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 0;
        long right = (long) times[0] * n;        
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (isValid(mid, n, times)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private boolean isValid(long target, int n, int[] times) {
        long sum = 0;
        for (int time : times) {
            sum+=target / time;
        }
        
        return sum < n;
    }
}