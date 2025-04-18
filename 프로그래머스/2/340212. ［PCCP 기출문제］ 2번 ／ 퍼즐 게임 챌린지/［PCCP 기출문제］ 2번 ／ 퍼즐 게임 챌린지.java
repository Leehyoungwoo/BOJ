import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        // diff <= leve time_cur만큼 시간 소모해서 해결
        // diff > level이면 퍼즐을 총 diff - level번 틀림
        // 퍼즐 틀릴때마다 time_cur만큼의 시간을 사용하며 추가로 time_prev만큼의 시간을 사용해 이전 퍼즐 다시 풀고 와야 함
        // 이전 퍼즐 다시 풀떄는 난이도에 상관없이 틀리지 않음
        // diff-level번 틀린 후에 퍼즐을 다시 풀면 time_cur만큼 사용해서 퍼즐 해결
        // 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도(level의) 최솟값
        // 이분탐색인거 같은데
        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canSolveAllProblem(mid, diffs, times, limit)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canSolveAllProblem(int level, int[] diffs, int[] times, long limit) {
        long sum = 0;
        int prev = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                sum += times[i];
            } else {
                sum += ((diffs[i] - level) * (times[i] + prev) + times[i]);
            }
            prev = times[i];
        }
        return sum <= limit;
    }
}