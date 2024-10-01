import java.util.*;

class Solution {
    static final int INF = 100_000_000; // 최대 요금보다 큰 값

    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 그래프 초기화
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }
        
        // 그래프에 요금 정보 추가
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            graph[u][v] = Math.min(graph[u][v], cost);
            graph[v][u] = Math.min(graph[v][u], cost);
        }

        // 다익스트라 알고리즘을 이용해 s에서 모든 노드까지의 최단 경로 계산
        int[] distanceFromS = dijkstra(n, s, graph);
        int[] distanceFromA = dijkstra(n, a, graph);
        int[] distanceFromB = dijkstra(n, b, graph);
        
        // 최저 예상 택시요금 계산
        int minCost = distanceFromS[a] + distanceFromS[b]; // 각자 이동
        for (int i = 1; i <= n; i++) {
            if (distanceFromS[i] < INF) {
                int totalCost = distanceFromS[i] + distanceFromA[i] + distanceFromB[i];
                minCost = Math.min(minCost, totalCost);
            }
        }

        return minCost;
    }

    // 다익스트라 알고리즘
    private int[] dijkstra(int n, int start, int[][] graph) {
        int[] distances = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0}); // {노드, 거리}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (int nextNode = 1; nextNode <= n; nextNode++) {
                if (graph[currentNode][nextNode] != INF) {
                    int newDistance = currentDistance + graph[currentNode][nextNode];
                    if (newDistance < distances[nextNode]) {
                        distances[nextNode] = newDistance;
                        pq.add(new int[]{nextNode, newDistance});
                    }
                }
            }
        }
        return distances;
    }
}
