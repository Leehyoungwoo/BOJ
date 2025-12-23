import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // 최소 시간을 구해야함 
        // 시간 = mid
        Arrays.sort(times);
        long answer = 0;
        long left = 1;
        long right = (long)n * times[times.length - 1];
        
        while (left <=right) {
            long mid = left + (right - left) / 2;
            if (isPossibleTime(mid, n, times)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossibleTime(long targetTime, int people, int[] times) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += targetTime / times[i];
            if (sum >= people) return true; 
        }
        
        return sum >= people;
    }
}