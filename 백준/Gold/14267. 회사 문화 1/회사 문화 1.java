import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static List<List<Integer>> graph;
    private static int[] praise;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] boss = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            boss[i] = Integer.parseInt(tokenizer.nextToken());
            if (boss[i] != -1) {
                graph.get(boss[i]).add(i);
            }
        }

        praise = new int[n + 1];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int employee = Integer.parseInt(tokenizer.nextToken());
            int praiseValue = Integer.parseInt(tokenizer.nextToken());
            praise[employee] += praiseValue;
        }
    }

    private static void findAnswer() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int child : graph.get(current)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.add(child);
                }
                praise[child] += praise[current];
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            answer.append(praise[i]).append("\n");
        }
        System.out.print(answer);
    }
}