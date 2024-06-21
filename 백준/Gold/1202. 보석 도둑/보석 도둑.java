import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static PriorityQueue<JW> jewels;
    private static PriorityQueue<Integer> bags;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        long sum = 0;
        PriorityQueue<Integer> availableJewels = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            int bagCapacity = bags.poll();
            while (!jewels.isEmpty() && jewels.peek().weight <= bagCapacity) {
                availableJewels.add(jewels.poll().value);
            }
            if (!availableJewels.isEmpty()) {
                sum += availableJewels.poll();
            }
        }
        System.out.println(sum);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        jewels = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        bags = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int m = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            jewels.add(new JW(m, v));
        }

        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(input.readLine());
            bags.add(c);
        }
    }
}

class JW {
    int weight;
    int value;

    public JW(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}