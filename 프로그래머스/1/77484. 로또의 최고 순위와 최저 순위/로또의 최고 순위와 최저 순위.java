import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        // 가능했던 최고 순위와 최저 순위를 리턴해야하고
        // 차피 66이니까 애지간하면 안넘을거 같고
        // 0이 지워진거니까 최고 순위는 겹치지 않는 나머지 당첨 번호가 됐다고 치고
        // 최저 순위는 0이 다 안됐다고 가정하고 순위를 보면 되지 않을까?
        int[] answer = new int[2];
        // 로또는 어차피 1 ~ 45
        
        // 제로 카운트를 세고
        // 지금까지 맞은걸 세
        // 등수 계산하는거지 기존의 등수
        // 그리고 나서 배열에 넣어줘
        int zeroCnt = 0;
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) {
                zeroCnt++;
            }
        }
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) {
                continue;
            }
            for (int j = 0; j < 6; j++) {
                if (lottos[i] == win_nums[j]) {
                    count++;
                }
            }
        }
        int rank = decideRank(count);
        int highRank = decideRank(count + zeroCnt);
        
        return new int[] {highRank, rank};
    }
    
    private int decideRank(int count) {
        if (count == 6) return 1;
        if (count == 5) return 2;
        if (count == 4) return 3;
        if (count == 3) return 4;
        if (count == 2) return 5;
        
        return 6;
    }
}