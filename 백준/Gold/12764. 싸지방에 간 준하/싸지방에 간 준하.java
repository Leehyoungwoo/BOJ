import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] times;
    private static PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        List<Integer> usage = new ArrayList<>();
        PriorityQueue<Integer> available = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int start = times[i][0];
            int end = times[i][1];

            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] <= start) {
                int[] event = priorityQueue.poll();
                available.offer(event[1]);
            }

            if (!available.isEmpty()) {
                int idx = available.poll();
                usage.set(idx, usage.get(idx) + 1);
                priorityQueue.offer(new int[]{end, idx});
            } else {
                usage.add(1);
                priorityQueue.offer(new int[]{end, usage.size() - 1});
            }
        }

        System.out.println(usage.size());
        for (int i = 0; i < usage.size(); i++) {
            System.out.print(usage.get(i) + " ");
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        times = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            times[i][0] = start;
            times[i][1] = end;
        }

        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
    }
}