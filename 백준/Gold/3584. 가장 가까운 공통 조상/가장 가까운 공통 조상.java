import java.io.*;
import java.util.*;

public class Main {

    private static int T;
    private static int N;
    private static int root;
    private static List<List<Integer>> graph;
    private static int[] set;
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            init(input);
            findAnswer();
        }
        System.out.println(stringBuilder);
    }

    private static void init(BufferedReader input) throws IOException {
        N = Integer.parseInt(input.readLine());
        set = new int[2];
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int parent = Integer.parseInt(tokenizer.nextToken());
            int child = Integer.parseInt(tokenizer.nextToken());
            graph.get(child).add(parent);
        }
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        set[0] = Integer.parseInt(tokenizer.nextToken());
        set[1] = Integer.parseInt(tokenizer.nextToken());
    }

    private static void findAnswer() {
        // 깊이 기준
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[set[0]] = true;
        queue.add(set[0]);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(Integer next : graph.get(cur)) {
                visited[next] = true;
                queue.add(next);
            }
        }
        if (visited[set[1]]) {
            stringBuilder.append(set[1]).append("\n");
            return;
        }
        queue.add(set[1]);
        visited[set[1]] = true;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(Integer next : graph.get(cur)) {
                if (visited[next]) {
                    stringBuilder.append(next).append("\n");
                    break;
                }
                queue.add(next);
                visited[next] = true;
            }
        }
    }
}