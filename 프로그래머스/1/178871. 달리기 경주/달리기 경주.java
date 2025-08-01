import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // 지금 순서와 콜링
        // 콜링은 콜링한 선수와 앞 선수가 위치가 바뀌는거임
        // 시간 복잡도가 콜링이 100만이네
        // 그러면 지금 배열이 있고 스왑 메서드 하나 정의해놓고 콜링 인덱스, 콜링 인덱스 - 1을 스왑해줘야 하는데
        // 인덱스를 알라면 맵도 있어야 함 왜냐면 순회하면서 바로바로 가져와야하니까
        // 콜링 순회하면서 위치 바뀌면 맵에 인덱스도 갱신해줘야하네
        Map<String, Integer> idxs = new HashMap<>();
        // 인덱스 기록
        for (int i = 0; i < players.length; i++) {
            String player = players[i];
            idxs.put(player, i);
        }
        
        // 콜링 순회
        for (int i = 0; i < callings.length; i++) {
            String calling = callings[i];
            int callingIdx = idxs.get(calling);
            swap(idxs, callingIdx - 1, callingIdx, players);
            // System.out.println(callingIdx);
            // print(players);
        }
        
        return players;
    }
    
    private void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    private void swap(Map<String, Integer> idxs, int from, int to, String[] players) {
        String temp = players[from];
        players[from] = players[to];
        players[to] = temp;
        // 인덱스 갱신
        idxs.put(players[from], from);
        idxs.put(players[to], to);
    }
}