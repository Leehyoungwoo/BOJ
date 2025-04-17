import java.util.*;

class Solution {
    private int[] comb = new int[5];
    private int answer;
    public int solution(int n, int[][] q, int[] ans) {
        // 1부터 n까지 nC5로 조합을 구함
        findComb(0, 1, q, ans, n);
        // 조합이 만들어지고 쿼리 안에 든거랑 answer랑 같으면 정답 후보 ++
        return answer;
    }
    
    private void findComb(int idx, int start, int[][] q, int[] ans, int n) {
        if (idx == comb.length) {
            if (isValid(q, ans, n)) {
                answer++;
            }
            // for (int i = 0; i < comb.length; i++) {
            //     System.out.print(comb[i] + " ");
            // }
            // System.out.println();
            return;
        }
        
        if (start == n + 1) {
            return;
        }
        
        comb[idx] = start;
        findComb(idx + 1, start + 1, q, ans, n);
        findComb(idx, start + 1, q, ans, n);
    }
    
    private boolean isValid(int[][] q, int[] ans, int n) {
        for (int i = 0; i < q.length; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < 5; j++) {
                set.add(q[i][j]);
                set.add(comb[j]);
            }
            
            if (set.size() != 10 - ans[i]) {
                return false;
            }
        }
        
        return true;
    }
}