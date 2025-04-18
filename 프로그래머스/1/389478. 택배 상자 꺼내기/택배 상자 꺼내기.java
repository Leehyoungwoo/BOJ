import java.util.*;

class Solution {
    
    private static final int[] direction = {1, -1};
    public int solution(int n, int w, int num) {
        int answer = 0;
        int number = 1;
        int idx = 1;
        int d = 0;
        int layer = 1;
        int targetIdx = 0;
        while (number <= n) {
            System.out.println("라운드" + number);
            System.out.println("인덱스" + idx);
            System.out.println("층" + layer);
            System.out.println("타겟 인덱스" + targetIdx);
            System.out.println("타겟 개수 " + answer);
            System.out.println();
            if (targetIdx == idx) {
                answer++;
            }
            
            if (num == number) {
                targetIdx = idx;
            }
    
            // 벽에서 방향 전환
            if (idx == w && d == 0) {
                d = 1;
                layer++;
                number++;
                // if (targetIdx == idx) {
                //     answer++;
                // }
            } else if (idx == 1 && d == 1) {
                d = 0;
                layer++;
                number++;
                // if (targetIdx == idx) {
                //     answer++;
                // }
            } else {
                // 인덱스를 옆으로 옮기자 일단
                idx+=direction[d];
                number++;
            }
        }
        
        return answer + 1;
        // 13 3 6
    }
}