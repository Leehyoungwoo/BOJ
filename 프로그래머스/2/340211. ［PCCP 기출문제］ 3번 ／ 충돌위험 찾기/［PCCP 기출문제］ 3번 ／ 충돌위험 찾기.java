import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int x = routes.length;
        int m = routes[0].length;   

        Queue<int[]> robots = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            int startPt = routes[i][0] - 1;
            robots.offer(new int[]{
                points[startPt][0], 
                points[startPt][1],  
                1,                   
                i                  
            });
        }

        int answer = 0;
        {
            Map<String, Integer> cnt = new HashMap<>();
            for (int[] r : robots) {
                String key = r[0] + "," + r[1];
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }
            for (int c : cnt.values()) {
                if (c >= 2) answer++;
            }
        }

        while (!robots.isEmpty()) {
            int size = robots.size();
            Map<String, Integer> posCount = new HashMap<>();
            Queue<int[]> nextQ = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                int[] cur = robots.poll();
                int rId = cur[3];
                int targetIdx = routes[rId][cur[2]] - 1;  
                int tR = points[targetIdx][0];
                int tC = points[targetIdx][1];

                if (cur[0] < tR)      cur[0]++;
                else if (cur[0] > tR) cur[0]--;
                else if (cur[1] < tC) cur[1]++;
                else if (cur[1] > tC) cur[1]--;

                String key = cur[0] + "," + cur[1];
                posCount.put(key, posCount.getOrDefault(key, 0) + 1);

                if (cur[0] == tR && cur[1] == tC) {
                    cur[2]++;  
                }
                if (cur[2] < m) {
                    nextQ.offer(cur);
                }
            }

            for (int c : posCount.values()) {
                if (c >= 2) answer++;
            }

            robots = nextQ;
        }

        return answer;
    }
}
