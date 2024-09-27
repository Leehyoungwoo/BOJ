import java.util.*;

class Solution {

    private int maxScoreDiff = 0;
    private int[] bestDistribution;

    public int[] solution(int n, int[] info) {
        bestDistribution = new int[11];

        backtrack(n, 0, new int[11], info);

        if (maxScoreDiff == 0) {
            return new int[]{-1};
        }

        return bestDistribution;
    }

    // 백트래킹 함수
    private void backtrack(int arrowsLeft, int index, int[] lionInfo, int[] apeachInfo) {
        if (index == 11 || arrowsLeft == 0) {
            lionInfo[10] += arrowsLeft;
            compareScores(lionInfo, apeachInfo);
            lionInfo[10] -= arrowsLeft;
            return;
        }

        if (arrowsLeft > apeachInfo[index]) {
            lionInfo[index] = apeachInfo[index] + 1;
            backtrack(arrowsLeft - lionInfo[index], index + 1, lionInfo, apeachInfo);
            lionInfo[index] = 0;
        }

        backtrack(arrowsLeft, index + 1, lionInfo, apeachInfo);
    }

    private void compareScores(int[] lionInfo, int[] apeachInfo) {
        int lionScore = 0, apeachScore = 0;

        for (int i = 0; i < 11; i++) {
            if (lionInfo[i] > apeachInfo[i]) {
                lionScore += (10 - i);
            } else if (apeachInfo[i] > 0) {
                apeachScore += (10 - i);
            }
        }

        int scoreDiff = lionScore - apeachScore;
        if (scoreDiff > maxScoreDiff || (scoreDiff == maxScoreDiff && isBetter(lionInfo))) {
            maxScoreDiff = scoreDiff;
            bestDistribution = lionInfo.clone();
        }
    }

    private boolean isBetter(int[] lionInfo) {
        for (int i = 10; i >= 0; i--) {
            if (lionInfo[i] > bestDistribution[i]) {
                return true;
            } else if (lionInfo[i] < bestDistribution[i]) {
                return false;
            }
        }
        return false;
    }
}
