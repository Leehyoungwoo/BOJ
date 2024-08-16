import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int[] dist = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i = 0; i < edge.length; i++) {
            int start = edge[i][0];
            int end = edge[i][1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        dist[1] = 0;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{1, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curIdx = cur[0];
            int curCost = cur[1];
            for (Integer next : graph.get(curIdx)) {
                if (dist[next] > curCost + 1) {
                    dist[next] = curCost + 1;
                    que.add(new int[] {next, curCost + 1});
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE) { 
                max = Math.max(max, dist[i]);
            }
        }
        
        for(int i = 0; i < dist.length; i++) {
            if (dist[i] == max) {
                answer++;
            }
        }
        return answer;
    }
}