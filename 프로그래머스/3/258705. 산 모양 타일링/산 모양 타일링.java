import java.util.*;

class Solution {
    
    private final int DIV_NUM = 10_007;
    
    public int solution(int n, int[] tops) {
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];

        a[1] = 1;
        if (tops[0] == 1) b[1] = 3;
        else b[1] = 2;

        for (int i = 2; i <= n; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % DIV_NUM;
            if (tops[i - 1] == 1) {
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % DIV_NUM;
            } else {
                b[i] = (a[i - 1] + b[i - 1] * 2) % DIV_NUM;
            }
        }
        return (a[n] + b[n]) % DIV_NUM;
    }
}