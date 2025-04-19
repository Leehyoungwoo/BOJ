import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static long[] card;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        card = new long[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            card[i] = Long.parseLong(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        PriorityQueue<Long> rule = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            rule.offer(card[i]);
        }
        for (int i = 0; i < m; i++) {
            long cardOne = rule.poll();
            long cardTwo = rule.poll();
            long sum = cardOne + cardTwo;
            rule.offer(sum);
            rule.offer(sum);
        }

        long sum = 0;
        while (!rule.isEmpty()) {
            sum+=rule.poll();
        }

        System.out.println(sum);
    }
}