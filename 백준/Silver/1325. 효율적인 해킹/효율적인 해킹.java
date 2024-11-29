import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] memory;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            graph.get(b).add(a);
        }

        memory = new int[n + 1];
    }

    private static void findAnswer() {
        for (int i = 1; i <= n; i++) {
            if (memory[i] == 0) { 
                memory[i] = findPosibleToHack(i);
                max = Math.max(max, memory[i]);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (memory[i] == max) {
                answer.append(i).append(" ");
            }
        }
        System.out.println(answer.toString().trim());
    }

    private static int findPosibleToHack(int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visited[start] = true;
        int count = 1;

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    que.offer(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        return count;
    }
}