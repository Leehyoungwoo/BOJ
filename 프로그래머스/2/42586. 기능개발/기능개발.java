import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> que = new LinkedList<>();
        Queue<Integer> cicd = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            que.offer(progresses[i]);
        }
        int idx = 0;
        while (!que.isEmpty()) {
            int cnt = 0;
            while (!que.isEmpty() && que.peek() >= 100) {
                int p = que.poll();
                cnt++;
                idx++;
            }
            if (cnt != 0) {
                cicd.offer(cnt);
            }

            for(int i = idx; i < progresses.length; i++) {
                int p = que.poll();
                p+=speeds[i];
                que.offer(p);
            }
        }
        answer = new int[cicd.size()];
        int size = cicd.size();
        for (int i = 0; i < size; i++) {
            answer[i] = cicd.poll();
        }
        return answer;
    }
}
