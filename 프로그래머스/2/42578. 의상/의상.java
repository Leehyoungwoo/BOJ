import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            String category = clothes[i][1];
            String cloth = clothes[i][0];
            
            if (!map.containsKey(category)) {
                map.put(category, new ArrayList<>());
            }
            map.get(category).add(cloth);
        }
        
        for (String s : map.keySet()) {
            answer *= (map.get(s).size() + 1);
        }
        
        return answer - 1;
    }
}
