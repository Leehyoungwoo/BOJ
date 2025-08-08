import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        Arrays.sort(book_time, Comparator.comparingInt(a -> toMinute(a[0])));
        
        // 순회를 해
        // pq에 종료 시간을 넣어
        // 지금 시작 시간 보다 종료 시간이 빨라 그럼 빼고 넣어 
        // 지금 시작 시간이 종료 시간보다 느려 그럼 넣어야지
        // 그리고 턴마다 answer pq.size()로 갱신하면 되지 않나?
        PriorityQueue<Integer> minEnd = new PriorityQueue<>();
        for (int i = 0; i < book_time.length; i++) {
            int start = toMinute(book_time[i][0]);
            int end = toMinute(book_time[i][1]) + 10;
            
            while (!minEnd.isEmpty() && start >= minEnd.peek()) {
                minEnd.poll();
            }
            
            minEnd.offer(end);
            answer = Math.max(answer, minEnd.size());
        }
        return answer;
    }
    
    private int toMinute(String time) {
        String[] sp = time.split(":");
        return Integer.parseInt(sp[0]) * 60 + Integer.parseInt(sp[1]);
    }
}