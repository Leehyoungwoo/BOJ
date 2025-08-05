import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        // 사과 상태에 따라 점수는 1~k
        // 한 상자에 m개씩 담아 포장
        // 가격은 상자 중 가장 낮은 점수 p * m
        // 가능한 많은 사과를 팔았을 때 얻을 수 있는 최대 이익 계산, 남는 사과를 버림
        // 가장 많은 이익을 얻으려면 역정렬해서 가치가 높은 사과는 최대한 비싸게 팔고 낮은 사과는 최대한 많이 받게 팔고 제일 낮은걸 버려야하는거 아닌가?
        // 역정렬하고 m개 진행하고 마지막 사과 가격 * m하자
        int answer = 0;
        Arrays.sort(score);
        int size = 0;
        int p = Integer.MAX_VALUE;
        for (int i = score.length - 1; i >= 0; i--) {
            int v = score[i];
            size++;
            p = Math.min(p, v);
            if (size == m) {
                answer+= p * m;
                p = Integer.MAX_VALUE;
                size = 0;
            }
        }
        
        return answer;
    }
}