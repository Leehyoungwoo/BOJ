import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int computer;
    private static int network;
    private static int[] arr;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        computer = Integer.parseInt(input.readLine());
        network = Integer.parseInt(input.readLine());
        arr = new int[computer + 1];
        for (int i = 0; i < computer + 1; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < network; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private static int findAnswer() {
        boolean[] visited = new boolean[computer + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                count++;
            }
        }
        return count - 1;
    }
}