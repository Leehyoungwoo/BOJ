import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] dist;


    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
    }

    private static void findAnswer() {
        List<List<Integer>> groups = new ArrayList<>();
        makeGroup(groups);
        int size = groups.size();
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(size).append("\n");
        List<Integer> list = new ArrayList<>();
        for (List<Integer> each : groups) {
            int best = Integer.MAX_VALUE;
            int target = -1;
            // 리스트 내부의 노드끼리는 이동 가능이 보장된다. 그게 그룹이니깐.
            for (int i = 0; i < each.size(); i++) {
                int from = each.get(i);
                int ecc = 0;
                for (int j = 0; j < each.size(); j++) {
                    int to = each.get(j);
                    // 거리가 최대인게
                    ecc = Math.max(ecc, dist[from][to]);
                }
                // 최소인 사람을 찾음
                if (ecc < best ) {
                    best = ecc;
                    target = from;
                }
            }
            list.add(target);
        }
        Collections.sort(list);
        for (Integer num : list) {
            answer.append(num).append("\n");
        }


        System.out.println(answer);
    }

    private static void makeGroup(List<List<Integer>> groups) {
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i, visited, groups);
            }
        }
    }

    private static void bfs(int cur, boolean[] visited, List<List<Integer>> groups) {
        Queue<Integer> que = new LinkedList<>();
        List<Integer> group = new ArrayList<>();
        que.offer(cur);
        visited[cur] = true;
        while (!que.isEmpty()) {
            int curNode = que.poll();
            group.add(curNode);
            for (int i = 1; i < dist[curNode].length; i++) {
                if (dist[curNode][i] == Integer.MAX_VALUE) {
                    continue;
                }
                if (!visited[i]) {
                    que.offer(i);
                    visited[i] = true;
                }
            }
        }

        groups.add(group);
    }
}