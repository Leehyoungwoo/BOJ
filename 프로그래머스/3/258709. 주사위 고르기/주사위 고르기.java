import java.util.*;

class Solution {

    int[][] globalDice;
    int maxWin = Integer.MIN_VALUE;
    int[] maxWinCase;
    int n;
    // 뽑힌 주사위 조합, 이게 아닌게 B...?
    List<int[]> diceComb;
    List<Integer> aScore;
    List<Integer> bScore;

    public int[] solution(int[][] dice) {
        int[] answer = new int[n / 2];
        init(dice);
        // 나올 수 있는 주사위 조합을 배열에 담음
        findDiceComb(0, 0, new int[n / 2]);
        // 나올 수 있는 점수를 찾아서 score A, B에 담고,
        // 이분 탐색으로 이기는 경우, 지는 경우를 찾아야 함
        findWinRate();
        return maxWinCase;
    }

    // 승수 세기
    private void findWinRate() {
        for (int[] combA : diceComb) {
            int[] combB = findNotDices(combA);
//            soutPrint(combB);
            aScore = new ArrayList<>(); // A가 선택한 주사위의 모든 조합
            bScore = new ArrayList<>(); // B가 선택한 주사위의 모든 조합
            combDice(0, combA, globalDice, 0, aScore);
            combDice(0, combB, globalDice, 0, bScore);
            // 순서대로 정렬
            Collections.sort(aScore);
            Collections.sort(bScore);

            // 이분탐색으로 승수 계산
            int totalCount = getTotalCount();

            // 만약 최고 승수가 갱신되면, 조합과 승수를 기록
            if (totalCount > maxWin) {
                maxWin = totalCount;
                updateAnswer(maxWinCase, combA);
            }
        }
    }
    private int getTotalCount() {
        int totalWinCount = 0;

        for (Integer a : aScore) {
            int left = 0;
            int right = bScore.size();

            while (left + 1 < right) {
                int mid = (left + right) / 2;

                if (a > bScore.get(mid)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            totalWinCount += left + 1;
        }

        return totalWinCount;
    }

    private void updateAnswer(int[] maxWinCase, int[] aDice) {
        for (int i = 0; i < maxWinCase.length; i++) {
            maxWinCase[i] = aDice[i] + 1;
        }
    }

    private int[] findNotDices(int[] dices) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < dices.length; i++) {
            visited[dices[i]] = true;
        }
        int[] temp = new int[n / 2];
        int idx = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                temp[idx] = i;
                idx++;
            }
        }
        return temp;
    }

    // a가 01 이면 b는 23이여야함
    // a가 02이면 b는 13
    void combDice(int idx, int[] dices, int[][] originDices, int sum, List<Integer> team) {
        if (idx == n / 2) {
            team.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            combDice(idx + 1, dices, originDices, sum + originDices[dices[idx]][i], team);
        }
    }

    private void init(int[][] dice) {
        diceComb = new ArrayList<>();
        aScore = new ArrayList<>();
        bScore = new ArrayList<>();
        n = dice.length;
        maxWinCase = new int[n / 2];
        globalDice = dice;
    }

    /// 나올 수 있는 주사위 조합
    // 주사위가 두 개이면 0, 1
    private void findDiceComb(int idx, int start, int[] arr) {
        if (idx == n / 2) {
            diceComb.add(arr.clone());
            return;
        }
        if (start == n) {
            return;
        }
        arr[idx] = start;
        findDiceComb(idx + 1, start + 1, arr);
        findDiceComb(idx, start + 1, arr);
    }

    private static void soutPrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}