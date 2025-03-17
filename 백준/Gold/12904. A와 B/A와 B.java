import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String s;
    private static String t;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        s = input.readLine();
        t = input.readLine();
    }

    private static void findAnswer() {
        // S -> T로 변환
        //문자열의 뒤에 A를 추가한다.
        //문자열을 뒤집고 뒤에 B를 추가한다.
        //되면 1 안되면 0
        // 연산을 역으로
        // 뒤에 A제거
        // 뒤에 B제거하고 뒤집기
        // T -> S가 가능해야함

        while(!t.equals(s)) {
            if (t.endsWith("A")) {
                t = t.substring(0, t.length()-1);
            } else if (t.endsWith("B")) {
                t = t.substring(0, t.length()-1);
                String fixT = new String(t);
                StringBuilder newT = new StringBuilder();
                for (int i = fixT.length() - 1; i >= 0; i--) {
                    newT.append(fixT.charAt(i));
                }
                t = newT.toString();
            }
            if (s.length() == t.length()) {
                break;
            }
        }

        if (s.equals(t)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}

