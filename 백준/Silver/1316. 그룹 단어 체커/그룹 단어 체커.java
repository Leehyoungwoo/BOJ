import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static String[] words;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = input.readLine();
        }
    }

    private static void findAnswer() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            String target = words[i];
            if (isTarget(target)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean isTarget(String target) {
        Set<Character> set = new HashSet<>();
        char prev = '0';
        for (char c : target.toCharArray()) {
            if (prev != '0' && c != prev && set.contains(c)) {
                return false;
            }
            set.add(c);
            prev = c;
        }

        return true;
    }
}
