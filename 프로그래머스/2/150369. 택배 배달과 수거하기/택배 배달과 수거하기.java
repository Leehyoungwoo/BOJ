import java.util.*;

class Solution {
    
    // n개의 집에 택배를 배달할거고
    // 재활용 택배 박스에 배달할거고
    // 배달 다니면서 빈 재활용 택배 상자 수거
    // 트럭에 최대 cap개의 상자를 실을 수 있음
    // n은 10만까지라 이중 포문 금지
    // 배달해야하는 박스와 수거해야할 박스 인덱스가 있고
    // 처음에는 먼데부터 배달하고 돌아오면서 먼데부터 수거하는게 이득 아닌가?
    // 1 0 3 1 2 cap = 4
    // 0 3 0 4 0
    // 3개를 실어서 배달 완료, 수거 완료 거리 5 * 2
    // 4개를 실어서 배달 완료 수거 완료 거리 3 * 2
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 스택 두개를 써서 배달 턴 수거 턴 잘 컨트롤하고 인덱스 잘 계산하면 되지 않을까?
        // 배달, 수거 박스 둘다 나눠서 cap이 안넘을떄까지 빼주고 거기까지 배달한거고 수거도 그만큼 하면 되는거 아닌가? 만약에 한집이 cap을 초과하면 cap만큼만 수거하고 다시 방문해야지 뭐. 
        // 그럼 방문해야하는 idx는 어떻게 컨트롤하지? 그냥 배열을 스택에 담는게 간단할거 같긴해
        Deque<Integer> deliver = new ArrayDeque<>();
        Deque<Integer> pick = new ArrayDeque<>();
        
        for (int i = 0; i < deliveries.length; i++) {
            deliver.push(deliveries[i]);
            pick.push(pickups[i]);
        }
        
        // 이동 거리 
        long answer = 0;
        // 인덱스 굳이 저장하지 않아도 0인건 안가도 되는거라는걸 생각하면 쉽지 않을까?
        while (!deliver.isEmpty() || !pick.isEmpty()) {
            while (!pick.isEmpty() && pick.peek() == 0) {
                pick.pop();
            }
            
            while (!deliver.isEmpty() && deliver.peek() == 0) {
                deliver.pop();
            }
            
            int longDist = Math.max(deliver.size(), pick.size());
            
            answer+=longDist * 2;
            
            int load = cap;
            
            while (load > 0 && !deliver.isEmpty()) {
                int cur= deliver.pop();
                if (cur <= load) {
                    load-=cur;
                } else {
                    deliver.push(cur - load);
                    load = 0;
                }
            }
            
            load = cap;
            while (load > 0 && !pick.isEmpty()) {
                int cur= pick.pop();
                if (cur <= load) {
                    load-=cur;
                } else {
                    pick.push(cur - load);
                    load = 0;
                }
            }
        }
        
        return answer;
    }
}
