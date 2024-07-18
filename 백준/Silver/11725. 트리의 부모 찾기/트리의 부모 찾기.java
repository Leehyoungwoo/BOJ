import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n + 1; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a  = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }

    private static void findAnswer() {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[n + 1];
        parent[1] = 1;
        boolean[] visited = new boolean[n + 1];
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                if (!visited[next]) {
                    parent[next] = cur;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 2; i < parent.length; i++) {
            answer.append(parent[i]).append("\n");
        }
        System.out.println(answer);
    }
}