import java.util.*;


class Solution {

    private final List<String> mbti = List.of("RT", "CF", "JM", "AN");
    Map<Character, Integer> score = new HashMap<>();

    public String solution(String[] survey, int[] choices) {
        String answer = "";
        // 점수 채점하기
        for (int i = 0; i < survey.length; i++) {
            String types = survey[i];
            char first = types.charAt(0);
            char second = types.charAt(1);
            int response = choices[i];
            if (response == 4) {
                continue;
            }

            if (response < 4) {
                score.put(first, score.getOrDefault(first, 0) + Math.abs(4 - response));
                continue;
            }

            if (response > 4) {
                score.put(second, score.getOrDefault(second, 0) + Math.abs(response - 4));
            }
        }

        // mbti 구성하기
        for (String types : mbti) {
            char first = types.charAt(0);
            char second = types.charAt(1);
            if (score.get(first) == score.get(second)) {
                int comparison = Character.compare(first, second);
                if (comparison < 0) {
                    answer += first;
                } else {
                    answer += second;
                }
                continue;
            }

            if (score.getOrDefault(first, 0) > score.getOrDefault(second, 0)) {
                answer += first;
            } else {
                answer += second;
            }
        }

        return answer;
    }
}