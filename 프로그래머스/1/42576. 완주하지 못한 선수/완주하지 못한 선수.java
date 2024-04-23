import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < participant.length; i++) {
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
        }
        
        for(int i = 0; i < completion.length; i++) {
            String man = completion[i];
            map.put(completion[i], map.getOrDefault(completion[i], 0) - 1);
            if(map.get(man) == 0) {
                map.remove(man);
            }  
        }
        Set<String> set = map.keySet();
        for(String name : set) {
            answer = name;
        }

        return answer;
    }
}
