import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = dfs(begin, target, words, new boolean[words.length]);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    private int dfs(String begin, String target, String[] words, boolean[] visited) {
        if (begin.equals(target)) {
            return 0;
        }
        
        int minCount = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canTransform(begin, words[i])) {
                visited[i] = true;
                int count = dfs(words[i], target, words, visited);
                if (count != Integer.MAX_VALUE) {
                    minCount = Math.min(minCount, count + 1);
                }
                visited[i] = false;
            }
        }
        
        return minCount;
    }
    
    private boolean canTransform(String begin, String word) {
        int diffCount = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }
}
