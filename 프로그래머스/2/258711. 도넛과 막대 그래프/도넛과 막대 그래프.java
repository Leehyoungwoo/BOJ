import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // node 저장
        Set<Integer> nodes = new HashSet<>();
        // [0][0]은 나가는거, [0][1]은 들어오는거
        Map<Integer, int[]> count = new HashMap<>();
        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int[] temp = count.getOrDefault(from, new int[2]);
            temp[0]++;
            count.put(from, temp);
            nodes.add(from);
            
            int[] temp2 = count.getOrDefault(to, new int[2]);
            temp2[1]++;
            count.put(to, temp2);
            nodes.add(to);
        }
        int nodeCount = nodes.size();
        for (Integer i : nodes) {
//도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상이면서 들어오는 간선이 없으면 정점
            if (count.get(i)[0] >= 2 && count.get(i)[1] == 0) {
                answer[0] = i;
                continue;
            }
            
            // 나가는 간선이 없으면 직선 그래프
            if (count.get(i)[0] == 0) {
                answer[2]++;
                continue;
            } 
            
            // 8자형 그래프는 들어오는게 2, 나가는게 2인 노드가 있으면 존재
            if (count.get(i)[0] >= 2 && count.get(i)[1] >= 2) {
                answer[3]++;
            }
        }
        // 총 새구는 정점에서 나가는 간선의 개수 - 나머지 그래프 = 도넛형 그래프
        answer[1] = count.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}
