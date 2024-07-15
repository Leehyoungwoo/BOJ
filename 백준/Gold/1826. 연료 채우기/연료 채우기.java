import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] gasStation;
    private static int l;
    private static int p;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        gasStation = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            gasStation[i][0] = Integer.parseInt(tokenizer.nextToken());
            gasStation[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(gasStation, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int l = Integer.parseInt(tokenizer.nextToken());
        int p = Integer.parseInt(tokenizer.nextToken());

        int cur = 0;
        int count = 0;
        int i = 0;
        int fuel = p;

        while (cur + fuel < l) {
            while (i < n && gasStation[i][0] <= cur + fuel) {
                pq.offer(gasStation[i][1]);
                i++;
            }

            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }
            fuel += pq.poll();
            count++;
        }

        System.out.println(count);
    }
}