import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        answer = findAnswer(dartResult);
        return answer;
    }

    public int findAnswer(String dartResult) {
        int score = 0;
        int num = 0;
        List<Integer> roundScore = new ArrayList<>();
        int area = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if ('0' <= ch && ch <= '9') {
                if (num != 0) {
                    num = 10;
                    continue;
                }
                num = ch - '0';
                continue;
            }

            if (ch == 'S' || ch == 'D' || ch == 'T') {
                area = findArea(ch);
                roundScore.add((int) Math.pow(num, area));
                num = 0;
                area = 0;
                continue;
            }

            if (ch == '#' || ch == '*') {
                int option = findOption(ch);
                if (option == -1) {
                    int reverse = roundScore.get(roundScore.size() - 1);
                    roundScore.remove(roundScore.size() - 1);
                    roundScore.add(reverse * option);
                    continue;
                }
                if(roundScore.size() == 1) {
                    int reverse = roundScore.get(0);
                    roundScore.remove(0);
                    roundScore.add(reverse * option);
                    continue;
                }
                int s = roundScore.get(roundScore.size() - 2);
                int s2 = roundScore.get(roundScore.size() - 1);
                roundScore.remove(roundScore.size() - 1);
                roundScore.remove(roundScore.size() - 1);
                roundScore.add(option * s);
                roundScore.add(option * s2);
            }
        }
        for (Integer i : roundScore) {
            score += i;
        }
        return score;
    }

    public int findArea(char c) {
        if (c == 'S') {
            return 1;
        }
        if (c == 'D') {
            return 2;
        }
        return 3;
    }

    public int findOption(char c) {
        if (c == '*') {
            return 2;
        }
        return -1;
    }
}