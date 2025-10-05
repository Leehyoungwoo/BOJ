import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] nums;

    public static void main(String[] args) throws Exception {
        init();
        solve();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        nums = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void solve() {
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();

        for (int x : nums) {
            if (x > 0) plus.offer(x);
            else minus.offer(x);
        }

        int maxDist = 0;
        if (!plus.isEmpty()) maxDist = Math.max(maxDist, plus.peek());
        if (!minus.isEmpty()) maxDist = Math.max(maxDist, Math.abs(minus.peek()));

        long total = 0;

        while (!plus.isEmpty()) {
            int far = plus.peek();
            total += far * 2L;
            for (int i = 0; i < m && !plus.isEmpty(); i++) {
                plus.poll();
            }
        }

        while (!minus.isEmpty()) {
            int far = Math.abs(minus.peek());
            total += far * 2L;
            for (int i = 0; i < m && !minus.isEmpty(); i++) {
                minus.poll();
            }
        }

        total -= maxDist;

        System.out.println(total);
    }
}