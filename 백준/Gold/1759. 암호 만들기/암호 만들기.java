import java.io.*;
import java.util.*;

public class Main {

    private static int l;
    private static int c;
    private static char[] alphabet;
    private static Set<Character> set;
    private static char[] string;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        init();
        String answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        l = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        String[] in = input.readLine().split(" ");
        alphabet = new char[c];
        string = new char[l];
        for (int i = 0; i < c; i++) {
            alphabet[i] = in[i].charAt(0);
        }
    }

    private static String findAnswer() {
        answer = new StringBuilder();
        // 우선 알파벳 순으로 정렬
        Arrays.sort(alphabet);
        // 모음 Set 만들어놓자
        set = new HashSet<>();
        set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        // 최소 한개의 모음과 최소 두 개의 자음으로 l길이의 문자열을 만들어야함, String을 매 재귀에 호출해도 될거 같긴 한데 굳이 그러지말자
        // 카운터를 관리하고, visited도 써야할듯
        makeWord(0, 0, 0, 0);

        return answer.toString();
    }

    private static void makeWord(int idx, int start, int 모음카운터, int 자음카운터) {
        if (idx == l) {
            if (모음카운터 < 1 || 자음카운터 < 2) {
                return;
            }
            answer.append(new String(string)).append("\n");
            return;
        }

        if (start == c) {
            return;
        }

        if (set.contains(alphabet[start])) {
            // 모듬 케이스
            string[idx] = alphabet[start];
            makeWord(idx + 1, start + 1, 모음카운터 + 1, 자음카운터);
            makeWord(idx, start + 1, 모음카운터 , 자음카운터);
        } else {
            // 자음
            string[idx] = alphabet[start];
            makeWord(idx + 1, start + 1, 모음카운터, 자음카운터 + 1);
            makeWord(idx, start + 1, 모음카운터 , 자음카운터);
        }
    }
}