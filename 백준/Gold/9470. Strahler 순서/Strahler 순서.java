import java.io.*;
import java.util.*;

public class Main {

    private static int t;
    private static int k, m, p;
    private static List<List<Integer>> graph;
    private static List<Map<Integer, Integer>> strahler;
    private static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        t = Integer.parseInt(input.readLine());

        for (int i = 0; i < t; i++) {
            init(input);
            int output = findAnswer();
            answer.append(k).append(" ").append(output).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        k = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        p = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        strahler = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            graph.add(new ArrayList<>());
            strahler.add(new HashMap<>());
        }

        indegree = new int[m + 1];
        for (int i = 0; i < p; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(to);
            indegree[to]++;
        }
    }

    private static int findAnswer() {
        int max = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 1; i <= m; i++) {
            if (indegree[i] == 0) {
                deque.addLast(new int[] {i, 1});
            }
        }

        while (!deque.isEmpty()) {
            int[] cur =  deque.poll();
            int curNode = cur[0];
            int s =  cur[1];
            max = Math.max(max, s);
            for (int next : graph.get(curNode)) {
                strahler.get(next).put(s, strahler.get(next).getOrDefault(s, 0) + 1);
                indegree[next]--;
                if (indegree[next] == 0) {
                    int nextS = Integer.MIN_VALUE;
                    for (Map.Entry<Integer, Integer> entry : strahler.get(next).entrySet()) {
                        if (entry.getValue() >= 2) {
                            nextS = Math.max(nextS, entry.getKey() + 1);
                        } else {
                            nextS = entry.getKey();
                        }
                    }
                    deque.offer(new int[] {next, nextS});
                }
            }
        }

        return max;
    }
}