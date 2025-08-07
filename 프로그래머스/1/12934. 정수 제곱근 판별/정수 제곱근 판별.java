import java.util.*;

class Solution {
    public long solution(long n) {
        for (long i = 1; i <= (long) Math.sqrt(n); i++) {
            if (i * i == n) {
                return (i + 1) * (i + 1); // pow 대신 정수 계산으로
            }
        }
        return -1;
    }
}
