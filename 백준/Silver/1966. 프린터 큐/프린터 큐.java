import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;
    private static int n, m;
    private static PriorityQueue<int[]> priorityQueue;
    private static Queue<int[]> queue;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        answer = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            init(input);
            findAnswer();
            answer.append('\n');
        }
        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[1]).reversed()
        );
        queue = new LinkedList<>();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(tokenizer.nextToken());
            int[] doc = new int[] {i, value};
            priorityQueue.add(doc);
            queue.add(doc);
        }
    }

    private static void findAnswer() {
        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] == priorityQueue.peek()[1]) {
                priorityQueue.poll();
                count++;
                if (cur[0] == m) {
                    answer.append(count);
                    return;
                }
            } else {
                queue.add(cur);
            }
        }
    }
}