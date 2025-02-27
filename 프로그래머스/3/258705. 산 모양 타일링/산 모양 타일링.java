// 삼각
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
