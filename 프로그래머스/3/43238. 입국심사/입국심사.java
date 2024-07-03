import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long)n * times[0];
        while (left <= right) {
            long mid = (right + left) / 2;
            if (canPlace(mid, times, n)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
    
    private boolean canPlace(long mid, int[] times, int n) {
        long sum = 0;
        for(int time : times) {
            sum += mid / time;
            if(sum >= n) {
                return true;
            }
        }
        return sum >= n;
    }
}