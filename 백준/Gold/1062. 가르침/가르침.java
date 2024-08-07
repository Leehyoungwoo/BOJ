import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static String[] words;
    private static int max = 0;
    private static boolean[] learned;

    public static void main(String[] args) throws IOException {
        init();
        findMax(0, 0);
        System.out.println(max);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = input.readLine();
        }
        if (k < 5) {
            System.out.println(0);
            System.exit(0);
        }
        // 기본 알파벳 배움 체크
        learned = new boolean[26];
        for (int i = 0; i < "antatica".length(); i++) {
            learned["antatica".charAt(i) - 'a'] = true;
        }
        k -= 5;
    }

    private static void findMax(int idx, int count) {
        if (count == k) {
            int readable = 0;
            for (String word : words) {
                boolean canRead = true;
                for (char c : word.toCharArray()) {
                    if (!learned[c - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    readable++;
                }
            }
            max = Math.max(max, readable);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!learned[i]) {
                learned[i] = true;
                findMax(i + 1, count + 1);
                learned[i] = false;
            }
        }
    }
}