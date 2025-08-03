import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        // keyMap이 있고
        // 순서대로 특정 키를 몇번 눌러야하는지가 저장되어 있고
        // 이걸 토대로 targets을 완성시키려면 최소 몇번 눌러야 하는지 답을 구하는 문제
        // 키맵의 길이는 100, 타켓의 길이도 100
        // 못만드는 경우는 -1 return
        // 그냥 Map하나 만들어놓고 각 알파벳의 최소 인덱스 저장하면 되는거 아닌가?
        Map<Character, Integer> countMap = new HashMap<>();
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                countMap.put(c, Math.min(i + 1 , countMap.getOrDefault(c, Integer.MAX_VALUE)));
            }
        }
        // targets 순회하면서 넣어주자
        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            boolean flag = true;
            for (int j = 0; j < targets[i].length(); j++) {
                char c = targets[i].charAt(j);
                if (!countMap.containsKey(c)) {
                    flag = false;
                    break;
                }
                sum+=countMap.get(c);
            }
            if (!flag) {
                answer[i] = -1;
                continue;
            }
            
            answer[i] = sum;
        }
        
        return answer;
    }
}