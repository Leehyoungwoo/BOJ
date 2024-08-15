import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        int answer = 0;
        Arrays.sort(citations);
        for(int i = 0; i < citations.length; i++) {
            int currentHIndex = Math.min(citations[n - 1 - i], i + 1);
            answer = Math.max(answer, currentHIndex);
        }
        return answer;
    }
}