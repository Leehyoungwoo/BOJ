import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 최대 알고력과 코딩력 설정
        int maxAlp = 150;
        int maxCop = 150;

        // 문제의 요구조건으로 최대 알고력과 코딩력 결정
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // dp 배열 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // 초기 상태
        dp[alp][cop] = 0;

        // dp 배열 업데이트
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                // 현재 알고력과 코딩력으로 얻을 수 있는 시간 업데이트
                // 알고력 증가
                if (a + 1 <= maxAlp) {
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                }
                
                // 코딩력 증가
                if (c + 1 <= maxCop) {
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                }

                // 문제를 풀 수 있는 경우
                for (int[] problem : problems) {
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];

                    // 현재 알고력과 코딩력으로 문제를 풀 수 있는지 확인
                    if (a >= alp_req && c >= cop_req) {
                        int newAlp = Math.min(maxAlp, a + alp_rwd);
                        int newCop = Math.min(maxCop, c + cop_rwd);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[a][c] + cost);
                    }
                }
            }
        }

        // 모든 문제를 풀 수 있는 최소 시간 찾기
        int result = Integer.MAX_VALUE;
        for (int a = 0; a <= maxAlp; a++) {
            for (int c = 0; c <= maxCop; c++) {
                boolean canSolveAll = true;
                for (int[] problem : problems) {
                    if (a < problem[0] || c < problem[1]) {
                        canSolveAll = false;
                        break;
                    }
                }
                if (canSolveAll) {
                    result = Math.min(result, dp[a][c]);
                }
            }
        }
        
        return result;
    }
}
