import java.util.*;

class Solution {
    
    private List<String> list = new ArrayList<>();
    private List<String> answerList = new ArrayList<>();
    private boolean found = false;

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1])); 
        dfs("ICN", tickets, new boolean[tickets.length], 0);
        return answerList.toArray(new String[0]);
    }

    private void dfs(String begin, String[][] tickets, boolean[] visited, int count) {
        list.add(begin);
        if (count == tickets.length) {
            answerList = new ArrayList<>(list);
            found = true;
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(begin)) {
                visited[i] = true;
                dfs(tickets[i][1], tickets, visited, count + 1);
                if (found) return; 
                visited[i] = false;
            }
        }
        list.remove(list.size() - 1);
    }
}
