class Solution {
    public int[] solution(long n) {
        StringBuilder answer = new StringBuilder();
        answer.append(n);
        answer.reverse();
        int[] num = new int[answer.length()];
        for (int i = 0; i < num.length; i++) {
            num[i] = answer.toString().charAt(i) - '0';
        }
        
        return num;
    }
}