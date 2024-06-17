import java.util.*;

class Solution {
    
    private int[] per;
    private int cnt;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        per = new int[numbers.length];
        dfs(0, target, numbers);
        answer = cnt;
        return answer;
    }
    
    private void dfs(int idx, int target, int[] numbers) {
        if (idx == numbers.length) {
            // for(int i = 0; i < per.length; i++) {
            //     System.out.print(per[i] + " ");
            // }
            // System.out.println();
            int cal = caculate(numbers);
            if (cal == target) {
                cnt++;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            per[idx] = i;
            dfs(idx + 1, target, numbers);
        }

    }
    
    private int caculate(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < per.length; i++) {
            if(per[i] == 0) {
                sum+=numbers[i];
            } else {
                sum-=numbers[i];
            }
        }
        return sum;
    }
}