import java.util.*;

class Solution {
    
    int[] map;
    char[] arr;
    String[] globalSurvey;
    int[] globalChoices;
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        init(survey, choices);
        findAnswer();
        answer = new String(arr);
        return answer;
    }
    
    public void init(String[] survey, int[] choices) {
        map = new int[26];
        arr = new char[4];
        globalSurvey = survey;
        globalChoices = choices;
    }
    
    public void findAnswer() {
        // MBTI 별 점수 매기기
        for(int i = 0; i < globalSurvey.length; i++) {
            String qa = globalSurvey[i];
            int choice = globalChoices[i];
            char first = qa.charAt(0);
            char second = qa.charAt(1);
            if (choice <= 4) {
                map[first - 'A'] += (4 - choice);
            } else {
                map[second - 'A'] += (choice - 4);
            }
        }
        // 점수 계산하고 비교
        if (map['R' - 'A'] >= map['T' - 'A']) {
            arr[0] = 'R';
        } else {
            arr[0] = 'T';
        }
        
        if (map['C' - 'A'] >= map['F' - 'A']) {
            arr[1] = 'C';
        } else {
            arr[1] = 'F';
        }
        
        if (map['J' - 'A'] >= map['M' - 'A']) {
            arr[2] = 'J';
        } else {
            arr[2] = 'M';
        }
        
        if (map['A' - 'A'] >= map['N' - 'A']) {
            arr[3] = 'A';
        } else {
            arr[3] = 'N';
        }
    }
}