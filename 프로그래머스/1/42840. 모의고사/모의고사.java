import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] aA = {1, 2, 3, 4, 5};
        int a = 0;
        int[] bA = {2, 1, 2, 3, 2, 4, 2, 5};
        int b = 0;
        int[] cA = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int c= 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == aA[i % 5]) {
                a++;
            }
            if (answers[i] == bA[i % 8]) {
                b++;
            }
            if (answers[i] == cA[i % 10]) {
                c++;
            }
        }
        int max = Math.max(a, Math.max(b, c));
        int[] temp = {a, b, c};
        int count = 0;
        for(int i = 0; i < temp.length; i++) {
            if (temp[i] == max) count++; 
        }
        List<Integer> list = new ArrayList<>();
        if (a == max) list.add(1);
        if (b == max) list.add(2);
        if (c == max) list.add(3);
        Collections.sort(list);
        return list.stream().mapToInt(i -> i).toArray();
    }
}