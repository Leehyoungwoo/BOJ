import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 나랑 같은 알파벳이 언제 먼저 나왓는지 확인해야함
        // 이전에 나랑 같은게 없었으면 -1
        // 그러면 일단 모든 알파벳을 map에 -1로 채우고
        // s를 순회하면서 map의 값을 확인해서 지금 인덱스 - value하고 값을 다시 지금 인덱스로 채우면 되지 않을까?
        // 소문자만 있음
        Map<Character, Integer> map = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++) {
            map.put(c, -1);
        }
        int[] answer = new int[s.length()];
        // 이제 s 순회
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == -1) {
                answer[i] = -1;
                map.put(c, i);
                continue;
            }
            answer[i] = i - map.get(c);
            map.put(c, i);
        }
        
        return answer;
    }
}