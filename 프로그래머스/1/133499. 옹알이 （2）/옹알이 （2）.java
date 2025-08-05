import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        String[] sounds = {"aya", "ye", "woo", "ma"};
        int answer = 0;
        
        for (String word : babbling) {
            boolean valid = true;
            
            // 1. 연속 발음 체크
            for (String s : sounds) {
                if (word.contains(s + s)) { // 연속 같은 발음
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;
            
            // 2. 발음 제거
            for (String s : sounds) {
                word = word.replace(s, " ");
            }
            
            // 3. 남은 문자가 없으면 유효
            if (word.trim().isEmpty()) {
                answer++;
            }
        }
        return answer;
    }
}
