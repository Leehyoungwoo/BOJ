import java.util.*;

class Solution {

    private List<Integer> aDice = new ArrayList<>();
    private List<Integer> bDice = new ArrayList<>();
    private List<Integer> aScore = new ArrayList<>();
    private List<Integer> bScore = new ArrayList<>();
    private int maxWinToA = Integer.MIN_VALUE;
    private int[] maxWInComb = new int[2];

    public int[] solution(int[][] dice) {
        int[] answer = {};
        findComb(0, 1, dice);
        answer = maxWInComb;
        return answer;
    }

    private void findComb(int idx, int start, int[][] dice) {
        if (idx == dice.length / 2) {
//            System.out.println("a주사위" + aDice);
            findBDice(dice);
            makeScore(0, dice, 0, aScore, aDice);
            makeScore(0, dice, 0, bScore, bDice);
            Collections.sort(aScore);
            Collections.sort(bScore);
//            System.out.println("b주사위 :" + bDice);
//            System.out.println(" a점수 : " + aScore);
//            System.out.println("개수 :" + aScore.size());
//            System.out.println(" b점수 : " + bScore);
            // aScore 별로 b한테 몇번 이기는지? 기록. 가장 많이 이기는 조합을 max로 숫자와 조합 둘다 기록
            int aWin = 0;
            for (Integer aGot : aScore) {
                int count = findWinCount(aGot);
                aWin += count;
            }
            if (aWin > maxWinToA) {
                maxWinToA = aWin;
                maxWInComb = aDice.stream().mapToInt(Integer::intValue).toArray();
            }
//            System.out.println(aWin);
            // 재귀 종료되면서 점수 조합과 b 주사위 조합 초기화
            aScore.clear();
            bScore.clear();
            bDice.clear();
            return;
        }

        if (start == dice.length + 1) {
            return;
        }

        aDice.add(start);
        findComb(idx + 1, start + 1, dice);
        aDice.remove(aDice.size() - 1);
        findComb(idx, start + 1, dice);
    }

    private int findWinCount(Integer aGot) {
        int left = 0;
        int right = bScore.size() - 1;
        int cnt = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (aGot > bScore.get(mid)) {
                cnt = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return cnt;
    }

    private void makeScore(int idx, int[][] dice, int sum, List<Integer> score, List<Integer> personDice) {
        if (idx == dice.length / 2) {
            score.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            makeScore(idx + 1, dice, sum + dice[personDice.get(idx) - 1][i], score, personDice);
        }
    }

    private void findBDice(int[][] dice) {
        for (int i = 1; i < dice.length + 1; i++) {
            if (aDice.contains(i)) {
                continue;
            }
            bDice.add(i);
        }
    }
}