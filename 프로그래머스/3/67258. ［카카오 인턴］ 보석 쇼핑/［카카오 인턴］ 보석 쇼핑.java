import java.util.*;

class Solution {
    
    private Map<String, Integer> countMap;
    
    public int[] solution(String[] gems) {
        // 보석 숫자부터 찾아보자
        int n = gems.length;
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int totalGem = set.size();
        
        countMap = new HashMap<>();
        int have = 0;
        int left = 0;
        int bestLeft = 0;
        int bestRight = n - 1;
        int bestLen = n + 1;
        for (int right = 0; right < n; right++) {
            String gem = gems[right];
            countMap.put(gem, countMap.getOrDefault(gem, 0) + 1);
            if (countMap.get(gem) == 1) {
                // 방금 들어온거지
                have++;
            }
            
            while (have == totalGem) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestLeft = left;
                    bestRight = right;
                }
                String gl = gems[left];
                int count = countMap.get(gl);
                if (count == 1) {
                    countMap.remove(gl);
                    have--;
                } else {
                    countMap.put(gl, count - 1);
                }
                left++;
            }
        }
        
        return new int[] {bestLeft + 1, bestRight + 1};
    }
}