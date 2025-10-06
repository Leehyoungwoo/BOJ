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
                // 아래부터 올라가면서 빈칸이 아닌거 확인
                if (map[r][c] != '.') {
                    // 빈칸이 아닌걸 write 포인터에 넣고 포인터 증가
                    map[write][c] = map[r][c];
                    // write는 넣어야할 곳, r은 넣어야할 것(빈칸이 아닌것)
                    // 넣어야할 곳에 넣어야할 것이 들어가면 write 한칸 위로 --
                    // 빈칸을 스킵하면서 압축시키기. 넣어야할 것은 빈칸을 스킵하고 넣어야할 곳에 넣어주면 됨. 전부 빈칸이 아니라면 그대로 제자리에 들어가게 될 것
//빈칸이 있다면 write인 빈칸에 다음 문자인 r이 들어가는거지
                    if (write != r) {
                        map[r][c] = '.';
                    }
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
