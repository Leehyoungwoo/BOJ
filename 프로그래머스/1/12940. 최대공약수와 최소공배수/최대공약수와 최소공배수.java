import java.util.*;

class Solution {
    public int[] solution(int n, int m) {
        List<Integer> nL = new ArrayList<>();
        List<Integer> mL = new ArrayList<>();
    
        find(n, nL);
        find(m, mL);
        Collections.sort(nL);
        Collections.sort(mL);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nL.size(); i++) {
            for (int j = 0; j < mL.size(); j++) {
                if (nL.get(i).equals(mL.get(j)))  {
                    max = Math.max(max, nL.get(i));
                }
            }
        }
        
        if (max == Integer.MIN_VALUE) {
            return new int[] {1, n * m};
        }
        
        return new int[] {max, (n * m) / max};
    }
    
    private void find(int n, List<Integer> list) {
        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    list.add(i);
                    continue;
                } 
                list.add(i);
                list.add(n / i);
            }
        }
    }
}