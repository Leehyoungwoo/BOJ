import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        // 글자를 읽을거고 첫 글자는 x임
        // 왼쪽에서 오른쪽으로 읽어나가면서 x와 x가 아닌 다른 글자가 나온 횟수를 각각 세다가 같아지면 멈추고 분리
        // 남은거에서 반복
        // 더 읽을 글자가 없으면 종료
        while (s.length() != 0) {
            int idx = 0;
            char x = s.charAt(idx);
            int xCnt = 1;
            int extra = 0;
            while (xCnt != extra) {
                idx++;
                if (idx >= s.length()) {
                    break;
                }
                char c = s.charAt(idx);
                if (x == c) {
                    xCnt++;
                } else {
                    extra++;
                }
                
            }
            answer++;
            if (idx >= s.length()) {
                break;
            }
            s = s.substring(idx + 1);
        }
        
        return answer;
    }
}