import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2, s.length() - 2);
        String[] arr = s.split("\\},\\{");
        answer = new int[arr.length];
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        int idx = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            String target = arr[i];
            String[] temp = target.split(",");
            for (int j = 0; j < temp.length; j++) {
                int number = Integer.parseInt(temp[j]);
                if (!set.contains(number)) {
                    answer[idx] = number;
                    idx++;
                    set.add(number);
                    break;
                }
            }
        }
        return answer;
    }
}