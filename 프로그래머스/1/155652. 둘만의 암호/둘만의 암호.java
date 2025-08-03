import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        // s를 뒤집을건데
        // index만큼 뒤의 알파벳으로 변경해줄거고
        // skip안에 들어가있으면 생략
        // set에 저장하고 
        // z넘어가면 다시 a로
        Set<Character> set = new HashSet<>();
        for (char c : skip.toCharArray()) {
            set.add(c);
        }
        // 번역을 해야하니까 빌더 하나 준비하고 
        StringBuilder answer = new StringBuilder();
        // 순회해보자
        for (char c : s.toCharArray()) {
            // 인덱스만큼 전진시킬건데 skip 제외, z넘어가면 a로 돌아오기
            for (int i = 0; i < index; i++) {
                c = (char) (c + 1);
                if (c > 'z') {
                    c = 'a';
                }
                while(set.contains(c)) {
                    c = (char) (c + 1);
                    if (c > 'z') {
                        c = 'a';
                    }
                }
                
                
            }
            answer.append(c);
        }
        
        return answer.toString();
    }
}