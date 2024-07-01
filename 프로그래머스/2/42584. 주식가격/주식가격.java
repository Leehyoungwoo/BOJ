import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> price = new LinkedList<>();
        Queue<Integer> diff = new LinkedList<>();
        for(int p : prices) {
            price.offer(p);
        }
        int idx = 0;
        while (!price.isEmpty()) {
            int p = price.poll();
            int sec = 0;
            for (int i = idx + 1; i < prices.length; i++) {
                if (p <= prices[i]) {
                    sec++;
                } else {
                    sec++;
                    break;
                }
            }
            answer[idx] = sec;
            idx++;
        }
        return answer;
    }
}