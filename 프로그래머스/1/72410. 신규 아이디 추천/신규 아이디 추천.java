import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        String recomendedId = findAnswer(answer, new_id);
        answer = recomendedId;
        return answer;
    }

    public String findAnswer(String answer, String new_id) {
        String id = level1(new_id);
        String id2 = level2(id);
        String id3 = level3(id2);
        String id4 = level4(id3);
        String id5 = level5(id4);
        String id6 = level6(id5);
        String id7 = level7(id6);
        return id7;
    }

    // 맞을듯
    public String level1(String new_id) {
        return new_id.toLowerCase();
    }

    // 맞을듯
    public String level2(String str) {
        int idx = 0;
        char[] arr = new char[str.length()];
        for (int i = 0; i < arr.length; i++) {
            if (isPossible(str.charAt(i))) {
                arr[idx] = str.charAt(i);
                idx++;
            }
        }
        return new String(arr).trim();
    }

    public boolean isPossible(char ch) {
        return ch == '.' || ch == '-' || ch == '_' || ('0' <= ch && ch <= '9')
                || ('a' <= ch && ch <= 'z');
    }

    public String level3(String s) {
        int maxCnt = 0;
        int cnt = 1;
        char[] arr = new char[s.length()];
        for (int i = 1; i < arr.length; i++) {
            if (s.charAt(i) == '.' && s.charAt(i - 1) == '.') {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
                continue;
            }
            cnt = 1;
        }
        String str = s;
        String point = ".";
        for (int i = maxCnt; i >= 2; i--) {
            for (int j = 0; j < i - 1; j++) {
                point = point + ".";
            }
            str = str.replace(point, ".");
            point = ".";
        }
        return str;
    }

    public String level4(String s) {
        if (s.equals(".")) {
            s = "";
            return s;
        }
        if (s.charAt(0) == '.') {
            s = s.substring(1);
        }
        if (s.charAt(s.length() - 1) == '.') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public String level5(String s) {
        if (s.isBlank()) {
            return "a";
        }

        return s;
    }

    public String level6(String s) {
        String id = s;
        if (s.length() >= 16) {
            id = s.substring(0, 15);
            while (id.endsWith(".")) {
                id = id.substring(0, id.length() - 1);
            }

        }
        return id;
    }

    public String level7(String s) {
        while (s.length() <= 2) {
            s = s + s.charAt(s.length() - 1);
        }
        return s;
    }
}