import java.util.*;

class Solution {
    
    private List<String> list;
    private char[] ch;
    private char[] temp;
    
    public int solution(String word) {
        int answer = 0;
        ch = new char[]{'A', 'E', 'I', 'O', 'U'};
        list = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            temp = new char[i];
            dfs(0, i);
        }
        Collections.sort(list);
        return list.indexOf(word) + 1;
    }
    
    private void dfs(int idx, int r) {
        if (idx == r) {
            // for(int i = 0; i < temp.length; i++) {
            //     System.out.print(temp[i] + " ");
            // }
            // System.out.println();
            list.add(new String(temp));
            return;
        }
        for (int i = 0; i < 5; i++) {
            temp[idx] = ch[i];  
            dfs(idx + 1, r);
        }
    }
}