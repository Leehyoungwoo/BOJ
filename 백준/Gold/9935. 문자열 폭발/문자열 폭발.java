import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String original;
    private static String bomb;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        original = input.readLine();
        bomb = input.readLine();
    }

    private static void findAnswer() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < original.length(); i++) {
            stack.push(original.charAt(i));

            if (stack.size() >= bomb.length()) {
                boolean flag = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if (bomb.charAt(j) != stack.get(stack.size() - bomb.length() + j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        if (stack.isEmpty()) {
            answer.append("FRULA");
        } else {
            for (char c : stack) {
                answer.append(c);
            }
        }

        System.out.println(answer);
    }
}