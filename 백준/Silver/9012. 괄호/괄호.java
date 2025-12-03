import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int n;
    private static String[] order;

    public static void main(String[] args) throws IOException {
        init();
        String answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        order = new String[n];
        for (int i = 0; i < n; i++) order[i] = input.readLine();
    }

    private static String findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < order.length; i++) {
            String output = selectYesOrNo(order[i]);
            answer.append(output).append("\n");
        }

        return answer.toString();
    }

    private static String selectYesOrNo(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }
}