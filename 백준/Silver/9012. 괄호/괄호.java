import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Queue<Character> queue = new LinkedList<>();
    private static int T;
    private static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            s = br.readLine();
            sb.append(findAnswer()).append("\n");
            queue.clear();
        }
        System.out.println(sb);
    }

    private static String findAnswer() {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(queue.size() != 0 && isMatched(c)) {
                queue.poll();
                continue;
            }
            queue.add(c);
        }

        if(queue.size() != 0 ) {
            return "NO";
        } else {
            return "YES";
        }
    }

    private static boolean isMatched(char c) {
        return c == ')' && queue.peek() == '(';
    }
}