import java.io.*;
import java.util.*;

public class Main {

    private static int tc;
    private static StringBuilder answer;
    private static String order;
    private static int n;
    private static Deque<String> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(input.readLine());
        answer = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            init(input);
        }
        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        order = input.readLine();
        n = Integer.parseInt(input.readLine());
        deque = new ArrayDeque<>();
        String in = input.readLine();

        if (n > 0) {
            in = in.substring(1, in.length() - 1);
            String[] arrs = in.split(",");
            deque.addAll(Arrays.asList(arrs));
        }

        boolean isReversed = false;
        for (char c : order.toCharArray()) {
            if (c == 'R') {
                isReversed = !isReversed;
            } else if (c == 'D') {
                if (deque.isEmpty()) {
                    answer.append("error").append("\n");
                    return;
                }
                if (isReversed) {
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
            }
        }

        printDeque(isReversed);
    }

    private static void printDeque(boolean isReversed) {
        answer.append("[");
        if (!deque.isEmpty()) {
            if (isReversed) {
                Iterator<String> iter = deque.descendingIterator();
                while (iter.hasNext()) {
                    answer.append(iter.next());
                    if (iter.hasNext()) answer.append(",");
                }
            } else {
                Iterator<String> iter = deque.iterator();
                while (iter.hasNext()) {
                    answer.append(iter.next());
                    if (iter.hasNext()) answer.append(",");
                }
            }
        }
        answer.append("]").append("\n");
    }
}