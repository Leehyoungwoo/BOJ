import java.io.*;
import java.util.*;

public class Main {

    private static int v;
    private static List<List<int[]>> graph;
    private static int maxNode;
    private static int maxLen;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(input.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            List<int[]> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < v; i++) {
            String[] s = input.readLine().split(" ");
            int node = Integer.parseInt(s[0]);
            for (int j = 1; j < s.length - 1; j += 2) {
                int to = Integer.parseInt(s[j]);
                int cost = Integer.parseInt(s[j + 1]);
                graph.get(node).add(new int[]{to, cost});
                graph.get(to).add(new int[] {node, cost});
            }
            int mark = Integer.parseInt(s[s.length - 1]);
        }
    }

    private static void findAnswer() {
        dfs(1, 0, new boolean[v + 1]);
        dfs(maxNode, 0, new boolean[v + 1]);
        System.out.println(maxLen);
    }

    private static void dfs(int node, int w, boolean[] visited) {
        visited[node] = true;
        if (w > maxLen) {
            maxNode = node;
            maxLen = w;
        }
        for (int[] arr : graph.get(node)) {
            if (!visited[arr[0]]) {
                dfs(arr[0], w + arr[1], visited);
            }
        }
    }
}