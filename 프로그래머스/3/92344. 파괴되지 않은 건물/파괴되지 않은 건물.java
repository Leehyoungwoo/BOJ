class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] damage = new int[n + 1][m + 1];

        for (int i = 0; i < skill.length; i++) {
            int t = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            // 공격
            if (t == 1) {
                damage[r1][c1] -= degree;
                damage[r1][c2 + 1] += degree;
                damage[r2 + 1][c1] += degree;
                damage[r2 + 1][c2 + 1] -= degree;
            }
            // 회복
            if (t == 2) {
                damage[r1][c1] += degree;
                damage[r1][c2 + 1] -= degree;
                damage[r2 + 1][c1] -= degree;
                damage[r2 + 1][c2 + 1] += degree;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) damage[i][j] += damage[i - 1][j];
                if (j > 0) damage[i][j] += damage[i][j - 1];
                if (i > 0 && j > 0) damage[i][j] -= damage[i - 1][j - 1];

                board[i][j] += damage[i][j];
            }
        }
        // 계산
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] >= 1) {
                    answer++;
                }
            }
        }
        return answer;
    }
}