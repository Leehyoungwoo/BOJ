import java.util.*;

class Solution {
    // 우선순위 컨트롤러 작동 방식은 요청이 들어오면 대기큐에 저장
    // 대기큐가 비어있지 않으면 대기큐에서 꺼내서 다스크에서 그 작업을 하는데
    // 소요 시간이 짧은 것, 요청 시간이 빠른 것, 작업 번호가 빠른 것 순으로 우선 순위가 높음
    // 작업을 시작하면 마칠떄까지 그 작업만 수행
    // 작업을 마치는 시점과 다른 작업 요청이 들어오는 시점이 겹치면 작업을 마치고 -> 요청이 들어온 작업을 큐에 저장 -> 높은 작업을 꺼내 작업을 시킴
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        int n = jobs.length;
        // 현재 시간 
        PriorityQueue<int[]> waitQue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        
        int time = 0;
        int idx = 0;
        int total = 0;
        
        while (idx < n || !waitQue.isEmpty()) {
            while (idx < n && jobs[idx][0] <= time) {
                // 대기큐에 지금 시간 time보다 시작이 빠르면 넣어주고
                waitQue.offer(jobs[idx]);
                idx++;
            }
            
            if (!waitQue.isEmpty()) {
                int[] cur = waitQue.poll();
                
                time+=cur[1];
                total += (time - cur[0]);
            } else {
                time = jobs[idx][0];
            }
        }
        
        return (int) (total / n);
    }
}