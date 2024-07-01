import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int cnt = 0;
        for (int i = 0; i < operations.length; i++) {
            if (cnt == 0) {
                max = new PriorityQueue<>(Collections.reverseOrder());
                min = new PriorityQueue<>();
            }
            String[] order = operations[i].split(" ");
            if (order[0].equals("I")) {
                max.offer(Integer.parseInt(order[1]));
                min.offer(Integer.parseInt(order[1]));
                cnt++;
                continue;
            }
            
            if (order[0].equals("D")) {
                if (cnt == 0) {
                    continue;
                }
                int num = Integer.parseInt(order[1]);
                if (num == 1) {
                    max.poll();
                    cnt--;
                    continue;
                }
                
                if (num == -1) {
                    min.poll();
                    cnt--;
                }
            }
        }
        if (cnt == 0) {
            answer = new int[]{0, 0};
            return answer;
        }
        answer = new int[]{max.peek(), min.peek()};
        return answer;
    }
}