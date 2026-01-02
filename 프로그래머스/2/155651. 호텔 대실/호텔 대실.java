import java.util.*;

class Solution {
    // 최소한의 객실만 사용해서 예약 손님을 받으려고 하느데
    // 퇴실 시간 기준 + 10분 청소하면 다음 손님 사용 가능 합니다
    // 입실 시간을 기준으로 정렬하고 현재 사용중인 손님을 우선순위큐에 퇴실 시간을 넣어서 관리해주면 
    // 입실 기준으로 들어오려는 손님의 시간과 현재 사용중인 손님들이 퇴실할 시간을 비교할 수 있습니다.
    // 만약 가장 먼저 나가는 손님보다 지금 들어올 손님이 들어오는 시간이 느리다면 사용할 방이 늘어납니다.
    // 시간 표현이 문자열로 되어있기 때문에 파싱이 필요한 상황입니다. 분단위로 합쳐서 간략하게 구현하겠습니다.
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        // 들어가는 손님을 입실 시간부터 관리해야하기 때문에 입실 시간을 기준으로 배열을 정렬하겠습니다.
        Arrays.sort(book_time, Comparator.comparing(a -> a[0]));
        // 이제 for문을 순회하면서 손님들의 퇴실 시간을 우선 순위 큐로 관리하도록 하겠습니다.
        
        for (int i = 0; i < book_time.length; i++) {
            String[] t = book_time[i];
            int start = timeConverter(t[0]);
            int end = timeConverter(t[1]);
            
            // 현재 있는 손님 중에 퇴실시간이 지금 손님의 입실시간보다 빠른 손님은 방을 빼야합니다.
            while (!endTime.isEmpty() && endTime.peek() + 10 <= start) {
                endTime.poll();
            }
            
            endTime.offer(end);
            
            answer = Math.max(answer, endTime.size());
        }
        
        return answer;
    }
    
    private int timeConverter(String time) {
        String[] t = time.split(":");
        
        return 60 * Integer.parseInt(t[0]) + Integer.parseInt(t[1]);
    }
}