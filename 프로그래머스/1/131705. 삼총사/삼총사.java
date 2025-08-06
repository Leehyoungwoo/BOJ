import java.util.*;

class Solution {
    
    private int[] comb = new int[3];
    private int answer;
    
    public int solution(int[] number) {
        makeComb(0, 0, number);        
        return answer;
    }
    
    private void makeComb(int idx, int start, int[] number) {
        if (idx == comb.length) {
            int sum = 0;
            for (int i : comb) {
                sum+=number[i];
            }
            
            if (sum == 0) {
                answer++;
            }
            
            return;
        }
        
        if (start == number.length) {
            return;
        }
        
        comb[idx] = start;
        makeComb(idx + 1, start + 1, number);
        makeComb(idx, start + 1, number);
    }
}