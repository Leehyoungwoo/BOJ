import java.util.*;

class Solution {
    public String solution(String p) {
        if (p.isEmpty()) {
            return p;
        }
        if (isCorrectString(p)) {
            return p;
        }
        return changeStringToCorrectString(p);
    }

    private String changeStringToCorrectString(String p) {
        if (p.isEmpty()) {
            return "";
        }

        // 1. u, v 분리
        int splitIdx = getBalancedIndex(p);
        String u = p.substring(0, splitIdx + 1);
        String v = p.substring(splitIdx + 1);

        // 2. u가 "올바른 괄호 문자열"인지 확인
        if (isCorrectString(u)) {
            return u + changeStringToCorrectString(v); // v를 변환하여 붙이기
        }

        // 3. 올바르지 않다면 변환 과정 수행
        StringBuilder newStr = new StringBuilder();
        newStr.append("(");
        newStr.append(changeStringToCorrectString(v));
        newStr.append(")");

        // 4. u의 첫 번째와 마지막 문자 제거 후 괄호 방향 뒤집기
        u = u.substring(1, u.length() - 1);
        u = reverseBrackets(u);
        newStr.append(u);

        return newStr.toString();
    }

    // "균형잡힌 괄호 문자열"의 인덱스를 찾는 함수
    private int getBalancedIndex(String p) {
        int left = 0, right = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                return i;
            }
        }
        return p.length() - 1;
    }

    // "올바른 괄호 문자열"인지 확인하는 함수
    private boolean isCorrectString(String p) {
        Stack<Character> stack = new Stack<>();
        for (char c : p.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 괄호 방향을 뒤집는 함수
    private String reverseBrackets(String s) {
        StringBuilder reversed = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                reversed.append(")");
            } else {
                reversed.append("(");
            }
        }
        return reversed.toString();
    }
}
