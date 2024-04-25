import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> list = new ArrayList<>();
        int[] answer = new int[commands.length];
        for(int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        for (int i = 0; i < commands.length; i++) {
            int s = commands[i][0] - 1;
            int e = commands[i][1] - 1;
            int index = commands[i][2] - 1;
            List<Integer> smallList = new ArrayList<>();
            for(int j = s; j <= e; j++) {
                int n = list.get(j);
                smallList.add(n);
            }
            Collections.sort(smallList);
            answer[i] = smallList.get(index);
        }
        
        return answer;
    }
}