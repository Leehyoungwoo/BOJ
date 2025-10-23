import java.util.*;

class Solution {
    // A, B 조합은 최대 225 => 5개 뽑으면 나머지 5개 자동 결정
    // 한명 주사위 가능 점수는 최대 7726, 6^10은 시간 초과 발생
    // 따로 구하고 정렬하고 A점수로 몇번 이길수 있는지 이분탐색, A 가능 점수의 최댓값이 B를 몇번 이기는지만 구하면 되는거 아닌가
    // 모든 조합에서 maxWin을 갱신해가면서 해당 주사위 조합도 함께 가져가면 될듯
    private int maxWin = Integer.MIN_VALUE;
    private int[] maxWinComb;
    private int n;
    private int[][] dices;
    private boolean[] picked;
    private int[] aComb;
    private int[] bComb;
    private List<Integer> aScore;
    private List<Integer> bScore;
    
    public int[] solution(int[][] dice) {
        init(dice);
        // dfs 돌려주고 
        findAnswer(0, 0);
        // 최고 승률의 조합을 반환해주면 됨 
        return maxWinComb;
    }
    
    private void findAnswer(int idx, int start) {
        if (idx == n / 2) {
            // 상태관리
            aScore.clear();
            bScore.clear();
            // bComb 만들자
            makebComb();
            findScores(0, aComb, aScore, new int[n / 2]);
            findScores(0, bComb, bScore, new int[n / 2]);
            // 정렬해야함
            Collections.sort(aScore);
            Collections.sort(bScore);
            int winCnt = 0;
            for (int a : aScore) {
                winCnt += findWinCount(a, bScore);
            }

            if (maxWin < winCnt) {
                maxWin = winCnt;
                for (int i = 0; i < maxWinComb.length; i++) {
                    maxWinComb[i] = aComb[i] + 1;
                }
            }
            return;
        }
        if (start == n) {
            return;
        }
            
        aComb[idx] = start;
        picked[start] = true;
        findAnswer(idx + 1, start + 1);
        picked[start] = false;
        findAnswer(idx, start + 1);
    }
    
    private int findWinCount(int a, List<Integer> list) {
        // 여기서 이분탐색으로 aBest보다 작은건 다 진거니까
        // aBest가 비긴걸 제외하려면 같은 경우 제일 왼쪽에 있어야 함
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < a) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // 여기서 가능한 조합을 리스트에 모두 담아야해, 중복 순열로 11... ~ 66...까지
    private void findScores(int idx, int[] comb, List<Integer> list, int[] num) {
        if (idx == n / 2) {
            // num안에 각 인덱스 별로 주사위 눈이 담겨져있음
            // comb안에 있는 조합으로 dices의 특정 dice의 눈금을 굴라서 점수를 합산
            int sum = 0;
            for (int i = 0; i < num.length; i++) {
                int number = num[i];
                int score = dices[comb[i]][number];
                sum+=score;
            }
            list.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            num[idx] = i;
            findScores(idx + 1, comb, list, num);
        }
    }
    
    private void makebComb() {
        int idx = 0;
        for (int i = 0; i < picked.length; i++) {
            if (!picked[i]) {
                bComb[idx] = i;
                idx++;
            }
        }
    }
    
    
    private void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        
        System.out.println();
    }
    
    private void init(int[][] dice) {
        n = dice.length;
        dices = dice;
        maxWinComb = new int[n / 2];
        picked = new boolean[n];
        aComb = new int[n / 2];
        // 매 재귀 종료조건에서 생성하지말고 재활용
        bComb = new int[n / 2];
        aScore = new ArrayList<>();
        bScore = new ArrayList<>();
    }
}