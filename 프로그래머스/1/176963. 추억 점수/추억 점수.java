import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = {};
        // 맵에다가 사람들의 추억 점수를 저장하고
        // photo를 순회하면서 다시 순회하면 만번이라 충분
        Map<String, Integer> score = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            score.put(name[i], yearning[i]);
        }
        answer = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (int j = 0; j < photo[i].length; j++) {
                if (!score.containsKey(photo[i][j])) {
                    continue;
                }
                sum+= score.get(photo[i][j]);
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}