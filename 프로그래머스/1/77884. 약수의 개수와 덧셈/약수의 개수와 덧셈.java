class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            int count = findCount(i);
            if (count % 2 == 0) {
                answer+=i;
            } else {
                answer-=i;
            }
        }
        return answer;
    }
    
    private int findCount(int num) {
        int count = 0;
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (i * i == num) {
                    count++;
                } else {
                    count+=2;
                }
            }
        }
        
        return count;
    }
}