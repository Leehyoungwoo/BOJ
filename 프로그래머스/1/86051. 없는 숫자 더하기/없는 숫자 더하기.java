class Solution {
    public int solution(int[] numbers) {
        int answer = 9 * 10 / 2;
        for (int i = 0; i < numbers.length; i++) {
            answer -= numbers[i];
        }
        
        return answer;
    }
}