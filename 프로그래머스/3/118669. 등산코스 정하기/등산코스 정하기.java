import java.util.*;

class Edge implements Comparable<Edge> {
    int to, cost;
    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

class Solution {

    private int lowIntensity = Integer.MAX_VALUE;
    private int lowIntensityNode = Integer.MAX_VALUE;
    private List<List<Edge>> graph = new ArrayList<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int cost = path[2];
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }
        dijkstra(gates, summits);
        
        return new int[]{lowIntensityNode, lowIntensity};
    }

    private void dijkstra(int[] gates, int[] summits) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] intensity = new int[graph.size()];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for (int i = 0; i < gates.length; i++) {
            pq.offer(new Edge(gates[i], 0));
            intensity[gates[i]] = 0;
        }

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.to;
            
            if (current.cost >lowIntensity) {
                continue;
            }


            for (Edge edge : graph.get(currentNode)) {
                int nextNode = edge.to;
                int nextCost = edge.cost;
                int maxIntensity = Math.max(current.cost, nextCost);

                if (Arrays.stream(summits).anyMatch(summit -> summit == nextNode)) {
                    if (maxIntensity < lowIntensity || (maxIntensity == lowIntensity && nextNode < lowIntensityNode)) {
                        lowIntensity = maxIntensity;
                        lowIntensityNode = nextNode;
                    }
                } else if (intensity[nextNode] > maxIntensity) {
                    intensity[nextNode] = maxIntensity;
                    pq.offer(new Edge(nextNode, maxIntensity));
                }
            }
        }
    }
}
