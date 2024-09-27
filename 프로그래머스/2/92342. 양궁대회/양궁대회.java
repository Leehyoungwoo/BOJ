import java.util.*;

class Solution {

    private int maxDiff = 0;
    private int[] bestShot;

    public int[] solution(int n, int[] info) {
        bestShot = new int[11];

        backTracking(n, 0, new int[11], info);
        // 라이언이 절대 어피치를 이길 수 없는 경우
        // 어피치가 전부 베스트 샷으로 맞춘게 아니더라도 절대 이길 수 없을 수 있음
        if (maxDiff == 0) {
            return new int[]{-1};
        }
        return bestShot;
    }

    private void backTracking(int leftArrow, int idx, int[] lionInfo, int[] apeachInfo) {
        // 남은 화살이 없거나 끝까지 다 쐈으면 종료
        if (leftArrow == 0 || idx == 11) {
            // 만약 끝까지 쐈는데 화살이 남았으면 0점에 다 쏴버리기
            lionInfo[10] += leftArrow;
            compareScore(lionInfo, apeachInfo);
            // 되돌리기
            lionInfo[10] -= leftArrow;
            return;
        }

        if (leftArrow > apeachInfo[idx]) {
            // 한발 더 쏴서 해당 점수 가져가기
            lionInfo[idx] = apeachInfo[idx] + 1;
            backTracking(leftArrow - lionInfo[idx], idx + 1, lionInfo, apeachInfo);
            // 백트래킹
            lionInfo[idx] = 0;
        }

        // 남은 화살이 apeach의 해당 점수를 못이기면 다음 점수를 노려야하니까
        backTracking(leftArrow, idx + 1, lionInfo, apeachInfo);
    }

    private void compareScore(int[] lionInfo, int[] apeachInfo) {
        int lionScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < 11; i++) {
            if (lionInfo[i] > apeachInfo[i]) {
                lionScore += 10 - i;
                // 어피치가 0이상인데 라이인이 어피치보다 크지 않으면 어피치 점수 get
            } else if (apeachInfo[i] > 0) {
                apeachScore += 10 - i;
            }
        }

        int diff = lionScore - apeachScore;
        if (diff > maxDiff || diff == maxDiff && isBetter(lionInfo)) {
            maxDiff = diff;
            bestShot = lionInfo.clone();
        }
    }

    private boolean isBetter(int[] lionInfo) {
        for (int i = 10; i >= 0; i--) {
            if (bestShot[i] < lionInfo[i]) {
                return true;
            } else if (bestShot[i] > lionInfo[i]) {
                return false;
            }
        }

        return false;
    }
}
