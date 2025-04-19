class Solution {
    
    private final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        String color = board[h][w];
        
        for(int[] dir : direction) {
            int nextH = h + dir[0];
            int nextW = w + dir[1];
            System.out.println("좌표: " + nextH + " " + nextW);
            System.out.println("색깔 " + color);
            if (isInRange(nextH, nextW, n, m) && board[nextH][nextW].equals(color)) {
                System.out.println("색깔 " + board[nextH][nextW]);
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isInRange(int r, int c, int h, int w) {
        return 0 <= r && r< h && 0 <= c && c < w;
    }
}