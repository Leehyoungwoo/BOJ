import java.util.*;

class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int id = n - 1;
        int ip = n - 1;
        
        long answer = 0;
        
        while (0 <= id || 0 <= ip) {
            while (0 <= id && deliveries[id] == 0) id--;
            while (0 <= ip && pickups[ip] == 0) ip--;
            
            if (id < 0 && ip < 0) break;
            
            long dis = Math.max(id, ip);
            answer += 2 * (dis + 1);
            
            int d = cap;
            while (0 <= id && d > 0) {
                if (deliveries[id] == 0) { id--; continue; }
                int use = Math.min(d, deliveries[id]);
                deliveries[id] -= use;
                d -= use;
            }
            
            int p = cap;
            while (ip >= 0 && p > 0) {
                if (pickups[ip] == 0) { ip--; continue; }
                int use = Math.min(p, pickups[ip]);
                pickups[ip] -= use;
                p -= use;
            }
        }
        
        return answer;
    }
}
