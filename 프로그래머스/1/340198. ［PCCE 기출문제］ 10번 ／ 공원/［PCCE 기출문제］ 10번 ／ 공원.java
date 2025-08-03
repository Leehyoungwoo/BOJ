import java.util.*;

class Solution {
        
    public int solution(int[] mats, String[][] park) {
        // 매트가 다 펴져있고
        // -1인데서 시작을 해야하며
        // 가장 큰 돗자리를 필 수 있는 자리 -> 가로 세로가 다 커버 가능해야함
        // 가지고 있는 돗자리는 5, 3, 2
        // 중간에 있는 것도 돗자리 놓는데에 방해가 되니까 검사를 할떄는 면적 전체 검사가 필요
        // park의 길이는 50, mats의 길이는 10 -> 완전 탐색 시뮬레이션 문제
        int answer = 0;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length; j++) {
                if (!park[i][j].equals("-1")) {
                    continue;
                }
                // 빈자리면 체크
                int m = 0;
                // 매트들을 순회하면서 가능한지 파악
                for (int mat : mats) {
                    boolean flag = true;
                    if (i + mat > park.length || j + mat > park[i].length) {
                        continue;
                    }
                    for (int r = i; r < i + mat; r++) {
                        for (int c = j; c < j + mat; c++) {
                            if (!park[r][c].equals("-1")) {
                                flag = false;
                            }
                        }
                    }
                    if (flag) {
                        m = Math.max(m, mat);
                    }
                }
                
                answer = Math.max(answer, m);
            }
        }
        
        return answer != 0 ? answer : -1;
    }
}