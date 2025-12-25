import java.util.*;

class Solution {
    // gems의 길이는 10만
    // 4종류의 보석이 있음
    // 모든 보석을 포함하는 짧은 구간을 살건데 시작 번호가 더 짧은 쪽을 리턴 번호는 인덱스 + 1
    // 완전 탐색 불가
    // 투포인터 불가
    // 이분 탐색 불가
    // 슬라이딩 윈도우? 
    // 베스트 길이, 베스트 left, 베스트 right
    // left, right를 두고 가보자
    // 만약에 조건이 만들어지면 right++; left++해서 다시 탐색
    
    public int[] solution(String[] gems) {
        int n = gems.length;
        Set<String> set = new HashSet<>();
        for (String g : gems) {
            set.add(g);
        }
        
        Map<String, Integer> count = new HashMap<>();
        for (String g : set) {
            count.put(g, 0);
        }
        
        int bestLeft = 0;
        int bestRight = 0;
        int minLen = Integer.MAX_VALUE;
        
        int formed = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (formed < set.size()) {
                count.put(gems[right], count.get(gems[right]) + 1);
                if (count.get(gems[right]) == 1) {
                    formed++;
                }
            } 
            
            while (formed == set.size()) {
                String key = gems[left];
                count.put(key, count.get(key) - 1);
                if (minLen > right - left + 1) {
                minLen = right - left + 1;
                bestLeft = left;
                bestRight = right;
                }   
                if (count.get(key) == 0) {
                    formed--;
                }
                left++;
            }

        }

        return new int[] {bestLeft + 1, bestRight + 1};
    }
    
    private boolean isAllContains(Set<String> set, Map<String, Integer> count) {
        for (String gem : set) {
            if (count.get(gem) == 0) {
                return false;
            }
        }
        
        return true;
    }
}