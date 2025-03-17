import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int k;
    private static String number;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        number = input.readLine();
    }

    private static void findAnswer() {
        Stack<Integer> stack = new Stack<>();
        int count = 0; 

        for (int i = 0; i < number.length(); i++) {
            int target = number.charAt(i) - '0';

            while (!stack.isEmpty() && stack.peek() < target && count < k) {
                stack.pop();
                count++;
            }
            stack.push(target);
        }

        while (count < k) {
            stack.pop();
            count++;
        }

        StringBuilder newNumber = new StringBuilder();
        while (!stack.isEmpty()) {
            newNumber.append(stack.pop());
        }

        System.out.println(newNumber.reverse().toString());
    }
}
