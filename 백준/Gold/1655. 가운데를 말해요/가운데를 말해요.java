import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static PriorityQueue<Integer> maxQue;
    private static PriorityQueue<Integer> minQue;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());

        maxQue = new PriorityQueue<>(Collections.reverseOrder());
        minQue = new PriorityQueue<>();

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(input.readLine());

            maxQue.add(num);

            if (!maxQue.isEmpty() && !minQue.isEmpty() && maxQue.peek() > minQue.peek()) {
                minQue.add(maxQue.poll());
            }

            if (maxQue.size() > minQue.size() + 1) {
                minQue.add(maxQue.poll());
            }
            if (minQue.size() > maxQue.size()) {
                maxQue.add(minQue.poll());
            }

            answer.append(maxQue.peek()).append("\n");
        }

        System.out.println(answer);
    }
}
