import java.util.*;
import java.io.*;

public class Main {

    private static int a;
    private static int b;
    private static int n;
    private static int m;
    private static int[] distance;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    private static void findAnswer() {
        if (a == b) {
            System.out.println(0);
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        distance[a] = 0;
        queue.offer(a);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                if (distance[next] > distance[cur] + 1) {
                    queue.offer(next);
                    distance[next] = distance[cur] + 1;
                }
            }
        }
        if (distance[b] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(distance[b]);
    }
}