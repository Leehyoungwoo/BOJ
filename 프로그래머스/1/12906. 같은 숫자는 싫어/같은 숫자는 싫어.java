import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> que = new LinkedList<>();
        for (int num : arr) {
            que.offer(num);
        }

        List<Integer> list = new ArrayList<>();
        Integer prev = null;

        while (!que.isEmpty()) {
            int num = que.poll();
            if (prev == null || prev != num) {
                list.add(num);
                prev = num;
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
