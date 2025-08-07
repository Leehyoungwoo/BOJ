class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        String num = x + "";
        int xNumber = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            xNumber+= c - '0';
        }
        
        return x % xNumber == 0;
    }
}