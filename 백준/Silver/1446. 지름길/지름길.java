import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int d;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        distance = new int[d + 1];
        for (int i = 0; i < d + 1; i++) {
            List<int[]> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            if (b <= d) {
                graph.get(a).add(new int[]{b, cost});
            }
        }
    }

    private static void findAnswer() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        for (int i = 0; i <= d; i++) {
            if (i > 0) {
                distance[i] = Math.min(distance[i], distance[i - 1] + 1);
            }
            for (int[] way : graph.get(i)) {
                int next = way[0];
                int cost = way[1];
                if (distance[next] > distance[i] + cost) {
                    distance[next] = distance[i] + cost;
                }
            }
        }
        System.out.println(distance[d]);
    }
}