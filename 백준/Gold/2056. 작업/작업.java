import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] time;
    private static int[] inDegree;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        graph = new ArrayList<>();
        inDegree = new int[n + 1];
        time = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int t = Integer.parseInt(tokenizer.nextToken());
            time[i] = t;
            int prevCnt = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < prevCnt; j++) {
                int prevJob = Integer.parseInt(tokenizer.nextToken());
                graph.get(prevJob).add(i);
                inDegree[i]++;
            }
        }
    }

    private static void findAnswer() {
        // 선행관계 없으면 동시에 진행 가능하다는데
        // visited 배열하고 indegree 같이 쓰면서 int[]로 추가할까?
        Queue<Integer> que = new LinkedList<>();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                dp[i] = time[i];
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Integer next : graph.get(cur)) {
                dp[next] = Math.max(dp[next], dp[cur] + time[next]);
                if (--inDegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}