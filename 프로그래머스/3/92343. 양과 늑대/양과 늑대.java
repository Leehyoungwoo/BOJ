import java.util.*;

class Solution {

    private List<List<Integer>> graph = new ArrayList<>();
    private int maxSheep = Integer.MIN_VALUE;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        // 그래프 완성
        makeGraph(edges, n);
        // Dfs로 찾으면 되지 않을까?
        findMaxSheep(0, info, 0, 0, new boolean[n], edges);
        return maxSheep;
    }

    private void findMaxSheep(int cur, int[] info, int sheep, int wolf, boolean[] visited, int[][] edge) {
        boolean[] nextVisited = visited.clone();
        nextVisited[cur] = true;

        if (info[cur] == 0) {
            sheep++;
            maxSheep = Math.max(maxSheep, sheep);
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        for (int[] e : edge) {
            if (nextVisited[e[0]] && !nextVisited[e[1]]) {
                findMaxSheep(e[1], info, sheep, wolf, nextVisited, edge);
            }
        }
    }

    private void makeGraph(int[][] edges, int n) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
    }
}