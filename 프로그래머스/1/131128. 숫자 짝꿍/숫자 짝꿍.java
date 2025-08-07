import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        // 3<= x와 y의 길이 <= 3백만
        // 공통된 수 찾아야함 X,Y
        // 개수도 같아야하고 중복은 가능함 
        // X에 1이 2개, 1이 한개면 짝궁은 1 한개
        // 어떻게 계산하지 visited할수도 없고
        // 개수 카운트에 시간복잡도 1로 존재 가능을 확인하려면 Map이네
        // 작은쪽에서 큰쪽 비교는 상관없나? 없겠다 어차피 쭉 돌면서 진행하는거니까
        // 기준점은 X로 잡자
        Map<Character, Integer> xMap = new HashMap<>();
        for (char c : X.toCharArray()) {
            xMap.put(c, xMap.getOrDefault(c, 0) + 1);
        }
        // y를 돌거고 돌면서 char가 키로 있으면서 값이 0이 아닌 경우에는 짝궁 문자열로 넣어주자
        // Integer로 바꿔서 넣어주자 sort하기 편하게
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < Y.length(); i++) {
            char c = Y.charAt(i);
            if (xMap.getOrDefault(c, 0) != 0) {
                list.add(c - '0');
                xMap.put(c, xMap.get(c) - 1);
            }
        }
        if (list.size() == 0) {
            return "-1";
        }
        // 내림 차순으로 정렬하고 붙이자
        StringBuilder answer = new StringBuilder();
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            answer.append(list.get(i));
        }
        // 0만 있으면 숫자를 만들 수 없잖아
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) count++;
        }
        
        if (count == answer.length()) return "0";
        
        return answer.toString();
    }
}