import java.util.*;

class Solution {
    
    private Map<String, Integer> map = new HashMap<>();
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // 회원은 매일 한가지 제품 할인
        // 일정금액 지불하면 10일동안 회원 자격
        // 할인하는 제품은 하루에 하나씩만 
        // discount길이 10만이네 
        // 그러면 want 길이이 윈도우에 number개만큼 모든게 포함되는지 확인하고
        // 있으면 answer++해서 끝까지 완주네
        // 미리 넣어놓자
        int n = number.length;
        // 10
        for (int i = 0; i < n; i++) {
            map.put(want[i], number[i]);
        }
        // 100만
        for (int i = 0; i <= discount.length - 10; i++) {
            if (allIn(discount, i, i + 9)) {
                System.out.println(i + "일");
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean allIn(String[] discount, int start, int end) {
        Map<String, Integer> day = new HashMap<>();
        // 10
        for (int i = start; i <= end; i++) {
            String target = discount[i];
            day.put(target, day.getOrDefault(target, 0) + 1);
        }
        // 커봐야 10
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (day.getOrDefault(entry.getKey(), 0) < (entry.getValue())) {
                return false;
            }
        }
        
        return true;
    }
}