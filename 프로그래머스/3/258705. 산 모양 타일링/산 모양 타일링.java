// 삼각형 두개가 겹치는 부분이 어떤 마름모 모양이냐에 따라서 다음에 들어갈 수 있는 도형이 결정남
// 두개의 디피 배열로 나누고 top이 있는 경우와 없는 경우를 고려하면 됨
class Solution {
    public int solution(int n, int[] tops) {
        final int MOD = 10007;
        
        int[] a = new int[n + 1]; 
        int[] b = new int[n + 1]; 
        
        // dp 초기화
        a[1] = 1;
        if (tops[0] == 1) {
            // 세가지 모양이 들어가니까
            b[1] = 3;
        } else {
            b[1] = 2;
        }
        
        for(int i = 2; i < n + 1; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % MOD;
            // 위에 삼각형이 있을 경우 top[i - 1] == 1;
            if (tops[i - 1] == 1) {
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % MOD;
            } else {
                b[i] = (a[i - 1] + b[i - 1] * 2) % MOD;
            }
        }
        
        return (a[n] + b[n]) % MOD;
    }
}
