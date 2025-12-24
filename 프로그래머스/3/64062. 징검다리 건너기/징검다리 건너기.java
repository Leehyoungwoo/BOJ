import java.util.*;

class Solution {
    // 징검다리를 건널건데
    // 디딜떄마다 가장 가까운 디딤돌로 갈거고 0이 되면 못밟음
    // 스톤즈 배열은 20만?
    // 사람이 건너는 수를 탐색할거니까 
    // 사람 건너는 수를 이분탐색으로 찾으면서 
    // 사람이 건너면 칸이 줄어들어 -> 돌 - 사람 <= 0이 연속 k보다 크거나 같으면 불가능한거잖아 
    // 그럼 가능한 사람중 제일 큰 값을 찾자
    
    public int solution(int[] stones, int k) {
        int left = 0;
        int right = 200_000_000;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // 수용 가능하면 
            if (canGo(mid, stones, k)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private boolean canGo(int people, int[] stones, int k) {
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            int left = stones[i] - people;
            if (left <= 0) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= k) {
                return false;
            }
        }
        
        return true;
    }
}