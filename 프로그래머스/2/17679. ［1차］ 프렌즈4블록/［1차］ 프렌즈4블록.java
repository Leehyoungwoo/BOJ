import java.util.*;

class Solution {
    private int row, col;
    private char[][] map;

    public int solution(int m, int n, String[] board) {
        init(m, n, board);
        int totalRemoved = 0;

        while (true) {
            boolean[][] mark = new boolean[row][col];
            int removedThisRound = markBlocks(mark); 
            if (removedThisRound == 0) break;         

            totalRemoved += removedThisRound;
            drop();                                   
        }

        return totalRemoved;
    }

    private int markBlocks(boolean[][] mark) {
        for (int r = 0; r < row - 1; r++) {
            for (int c = 0; c < col - 1; c++) {
                char ch = map[r][c];
                if (ch == '.') continue;
                if (map[r][c + 1] == ch && map[r + 1][c] == ch && map[r + 1][c + 1] == ch) {
                    mark[r][c] = mark[r][c + 1] = mark[r + 1][c] = mark[r + 1][c + 1] = true;
                }
            }
        }

        int removed = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (mark[r][c] && map[r][c] != '.') {
                    map[r][c] = '.';
                    removed++;
                }
            }
        }
        return removed;
    }

    private void drop() {
        for (int c = 0; c < col; c++) {
            int write = row - 1; 
            for (int r = row - 1; r >= 0; r--) {
                if (map[r][c] != '.') {
                    map[write][c] = map[r][c];
                    if (write != r) map[r][c] = '.';
                    write--;
                }
            }
        }
    }

    private void init(int m, int n, String[] board) {
        row = m;
        col = n;
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            map[i] = board[i].toCharArray();
        }
    }
}
