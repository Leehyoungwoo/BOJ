import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        // A -> B 침공 아이기스 군사 기지 융단 폭격
        // 요격함
        // X축에 평행한 폭격 미사일 (S, E)
        // Y축에 평행한 요격 미사일 발사
        // s와 e에서는 요격 불가능하대
        // 요격 미사일 최소 몇개 필요??
        // 실수도 필요하니까 start랑 end 평균값으로 계산해도 되나?
        // s, e는 억개래. 한번도 순회하면 안된다는거지
        // targets의 길이가 50만이니까 이건 순회 가능
        Arrays.sort(targets, Comparator.comparingInt(a -> a[0]));
        // 구간의 개수를 세는 문제야 어차피
        // start와 end가 겹치는건 다른 구간이라는 얘기고
        int start = targets[0][0];
        int end = targets[0][1];
        for (int i = 1; i < targets.length; i++) {
            int curStart = targets[i][0];
            int curEnd = targets[i][1];
            if (curStart <= end - 1) {
                start = Math.min(start, curStart);
                end = Math.min(end, curEnd);
            } else {
                answer++;
                start = curStart;
                end = curEnd;
            }
        }
        
        return answer;
    }
}