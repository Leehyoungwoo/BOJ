class Solution {
    
    private int answer = 0;
    private int[] comb;
    
    public int solution(int[] nums) {
        // 50개여도 50 50 50 곱해봐야 125만이네
        // 조합 세개 구하고 소수찾기 하면 되는거 아님?
        // 소수 찾기는 1000이여도 33이니까 3천만선에서 컷
        comb = new int[3];
        findComb(0, 0, nums);
        
        return answer;
    }
    
    private void findComb(int idx, int start, int[] nums) {
        if (idx == comb.length) {
            int sum = 0;
            for (int c : comb) {
                sum+=nums[c];
            }
            if (isTargetNumber(sum)) {
                answer++;
            }
            return;
        }
        
        if (start == nums.length) {
            return;
        }
        
        comb[idx] = start;
        findComb(idx + 1, start + 1, nums);
        findComb(idx, start + 1, nums);
    }
    
    private boolean isTargetNumber(int num) {
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (i == 1) {
                continue;
            }
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}