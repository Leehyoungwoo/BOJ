import java.util.*;

class Solution {

    private final int[][] DIR = {{1,0},{-1,0},{0,1},{0,-1}};
    private int[][] map;
    private int n, m;

    static class Res {
        final boolean win; // 현재 차례(항상 A 시점)의 승리 가능 여부
        final int moves;   // 양쪽이 움직인 총 횟수
        Res(boolean w, int mv){ win=w; moves=mv; }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        init(board);
        Res r = dfs(aloc[0], aloc[1], bloc[0], bloc[1]); // “항상 A 차례” 가정
        return r.moves;
    }

    // 항상 “현재 턴은 A”라고 가정: (ar,ac)가 현재 플레이어, (br,bc)가 상대
    private Res dfs(int ar, int ac, int br, int bc) {
        // 현재 칸이 이미 사라졌으면(밟을 수 없으면) 패배
        if (map[ar][ac] == 0) return new Res(false, 0);

        boolean canMove = false;
        int minWin = Integer.MAX_VALUE; // 이기는 수 중 최소
        int maxLose = 0;                // 지는 수 중 최대

        for (int[] d : DIR) {
            int na = ar + d[0], nc = ac + d[1];
            if (!in(na, nc) || map[na][nc] == 0) continue;
            canMove = true;

            // “현재 칸”을 지우고 한 칸 이동 → 턴 스왑해서 호출
            map[ar][ac] = 0;
            Res opp = dfs(br, bc, na, nc); // 상대가 A가 되도록 자리 바꿈
            map[ar][ac] = 1;               // 복원

            if (!opp.win) {
                // 상대가 지면 나는 이김 → 최소 턴 선택
                minWin = Math.min(minWin, opp.moves + 1);
            } else {
                // 상대가 이기면 나는 짐 → 최대 턴 선택
                maxLose = Math.max(maxLose, opp.moves + 1);
            }
        }

        if (!canMove) return new Res(false, 0);         // 한 칸도 못 가면 패배
        if (minWin != Integer.MAX_VALUE) return new Res(true,  minWin);
        return new Res(false, maxLose);
    }

    private boolean in(int r, int c){ return 0<=r && r<n && 0<=c && c<m; }

    private void init(int[][] board){
        n = board.length; m = board[0].length;
        map = new int[n][m];
        for (int i=0;i<n;i++) System.arraycopy(board[i], 0, map[i], 0, m);
    }
}
