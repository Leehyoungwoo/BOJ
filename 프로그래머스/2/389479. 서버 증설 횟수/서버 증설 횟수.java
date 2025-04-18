import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int server = 1;
        Queue<Integer> servers = new LinkedList<>();
        // 24시간동안 몇명의 유저가 들어오나 추적
        for(int i = 0; i < players.length; i++) {
            System.out.println(i + "시 시작");
            int player = players[i];
            System.out.println(player + "명 처리");
            // 시간이 지난 서버는 내리기
            while (!servers.isEmpty() && servers.peek() <= i) {
                System.out.println("서버 내리기!");
                servers.poll();
            }
            // 서버 가용 
            while ((servers.size() + 1) * m <= player) {
                System.out.println("서버 증설!");
                servers.offer(i + k);
                answer++;
            }
        }
        
        return answer;
    }
}