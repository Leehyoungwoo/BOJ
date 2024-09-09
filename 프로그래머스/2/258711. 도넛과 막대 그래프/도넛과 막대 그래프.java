import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // [0]은 나가는거 [1]은 들어가는거
        Map<Integer, int[]> map = new HashMap<>();

        // n개의 정점과 n개의 간선
        // 노드의 수
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < edges.length; i++) {
            int[] temp = map.getOrDefault(edges[i][0], new int[2]);
            temp[0]++;
            map.put(edges[i][0], temp);
            set.add(edges[i][0]);

            int[] temp2 = map.getOrDefault(edges[i][1], new int[2]);
            temp2[1]++;
            map.put(edges[i][1], temp2);
            set.add(edges[i][1]);
        }
        int nodeCount = set.size();
        for (Integer i : set) {
            //도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상이면서 들어오는 간선이 없으면 정점
            if (map.get(i)[0] >= 2 && map.get(i)[1] == 0) {
                answer[0] = i;
                continue;
            }

            // 나가는 간선이 없으면 직선 그래프
            if (map.get(i)[0] == 0 && map.get(i)[1] >= 1) {
                answer[2]++;
                continue;
            }

            // 8자형 그래프
            if (map.get(i)[0] >= 2 && map.get(i)[1] >= 2) {
                answer[3]++;
            }
        }

        // 총 그래프의 개수는 정점에서 나가는 간선의 개수
        answer[1] = map.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}
