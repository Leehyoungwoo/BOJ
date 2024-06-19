import java.util.*;

class Solution {
    public int solution(int[] money) {
        return Math.max(rob(money, 0, money.length - 2), rob(money, 1, money.length - 1));
    }
    
    private int rob(int[] money, int start, int end) {
        int prev = 0;
        int cur = 0;
        for(int i = start; i <= end; i++) {
            int temp = cur;
            cur = Math.max(prev + money[i], cur);
            prev = temp;
        }
        return cur;
    }
}
