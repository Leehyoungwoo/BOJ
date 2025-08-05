class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        // n의 인덱스는 1~n까지고
        // 룰러의 길이 m
        // 다시 칠하기로 한 구역은 적어도 한번 페인트칠 해야함
        // 룰러 칠하는 횟수 최소화
        // m, n은 10만이라 제곱하면 10억이 넘어감 시간복잡도 O(N)으로 풀어야함
        // 섹션을 순회하면서 빈곳을 찾으면 지금 인덱스 + m - 1로 여기까지 칠해졌다고 표시, count + 1
        // 섹션 순회하면서 그거보다 같거나 작으면 스킵
        // 그러면 되지 않나?
        int colored = 0;
        for (int i = 0; i < section.length; i++) {
            int idx = section[i];
            if (idx <= colored) {
                continue;
            }
            colored = idx + m - 1;
            answer++;
        }
        
        return answer;
    }
}