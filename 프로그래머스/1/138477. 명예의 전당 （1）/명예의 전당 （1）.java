import java.util.*;

class Solution {
    
    public int[] solution(int k, int[] score) {
        // 점수가 들어올때마다 제일 낮은 점수를 배열에 담아줘야함
        // 우선순위큐를 사용하면서 넣고 k넘으면 빼고 배열에 peek넣어주면 되겠다
        int[] answer = new int[score.length];
        PriorityQueue<Integer> scores = new PriorityQueue<>();
        for (int i = 0; i < score.length; i++) {
            int s = score[i];
            scores.offer(s);
            if (scores.size() > k) {
                scores.poll();
            }
            answer[i] = scores.peek();
        }
        
        return answer;
    }
}