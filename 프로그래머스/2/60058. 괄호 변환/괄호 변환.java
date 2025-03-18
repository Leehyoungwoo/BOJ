import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        //입력이 빈 문자열인 경우, 빈 문자열을 반환
        if (p.isBlank()) {
            return p;
        }
        
        if (isCorrectString(p)) {
            return p;
        }
        
        answer = changeStringToCorrectString(p);
        return answer;
    }
    
    private String changeStringToCorrectString(String p) {
        if (p.isEmpty()) {
            return "";
        }
        int idx = findIdx(p);
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);
        if (isCorrectString(u)) {
            return u + changeStringToCorrectString(v);
        }
        StringBuilder newOne = new StringBuilder();
        newOne.append('(');
        newOne.append(changeStringToCorrectString(v));
        newOne.append(')');
        u = u.substring(1, u.length() - 1);
        u = reverse(u);
        newOne.append(u);
        return newOne.toString();
    }
    
    private String reverse(String u) {
        StringBuilder reverseOne = new StringBuilder();
        for(int i = 0; i < u.length(); i++) {
            char c = u.charAt(i);
            if (c == '(') {
                reverseOne.append(')');
            } else {
                reverseOne.append('(');
            }
        }
        
        return reverseOne.toString();
    }
    
    private int findIdx(String p) {
        int idx = 0;
        int left = 0;
        int right = 0;
        for (char c : p.toCharArray()) {
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left != 0 && right != 0 && left == right) {
                break;
            }
            idx++;
        }
        return idx;
    }
    
    private boolean isCorrectString(String p) {
        Stack<Character> stack = new Stack<>();
        for (char c : p.toCharArray()) {
            if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                continue;
            }
            stack.push(c);
        }
        return stack.isEmpty();
    }
}
