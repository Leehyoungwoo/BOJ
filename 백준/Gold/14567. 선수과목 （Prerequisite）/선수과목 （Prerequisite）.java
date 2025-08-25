import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<List<Integer>> graph;
    private static int[] inDegree;
    private static int[] se;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(v);
            inDegree[v]++;
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        Queue<Integer> que = new LinkedList<>();
        se = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
                se[i] = 1;
            }
        }

        makeOrder(que);

        for (int i = 1; i <= n; i++) {
            answer.append(se[i]).append(" ");
        }

        System.out.println(answer);
    }

    private static void makeOrder(Queue<Integer> que) {
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Integer next : graph.get(cur)) {
                se[next] = Math.max(se[next], se[cur] + 1);
                if (--inDegree[next] == 0) {
                    que.offer(next);
                }
            }
        }
    }
}