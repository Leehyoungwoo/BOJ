import java.util.*;

class Solution {

    // D L R U 순서
    final int[][] direction = {{1, 0}, {0, -1}, {0, 1}, {-1,0}};
    char[][] map;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        // 초기화
        map = new char[n + 1][m + 1];
        map[x][y] = 'S';
        map[r][c] = 'E';
        answer = findAnswer(x, y, r, c, k, "");
        return answer.equals("") ? "impossible" : answer;
    }
    // DFS로 푼다고 치면
    // (k - 내 지금 지점랑 엔딩 지점이랑 거리)가 0보단 커야되고, 짝수여야 한다
    public String findAnswer(int curR, int curC, int r, int c, int k, String way) {
        if (curR == r && curC == c) {
            if (k == 0) return way;
            else {
                if (!remainWay(curR, curC, r, c, k)) {
                    return "";
                }
            }
        }

        if (!remainWay(curR, curC, r, c, k)) {
            return "";
        }

        for (int i = 0; i < direction.length; i++) {
            int[] dir = direction[i];
            int nextR = curR + dir[0];
            int nextC = curC + dir[1];
            if (isInRange(nextR, nextC)) {
                String result = findAnswer(nextR, nextC, r, c, k - 1, way + direc(dir));
                if (!result.equals("")) {
                    return result;
                }
            }
        }
        return "";
    }

    public char direc(int[] d) {
        if(d[0] == 1 && d[1] == 0) {
            return 'd';
        }

        if(d[0] == 0 && d[1] == -1) {
            return 'l';
        }

        if(d[0] == 0 && d[1] == 1) {
            return 'r';
        }

        return 'u';
    }

    public boolean remainWay(int curR, int curC, int r, int c, int k) {
        int left = Math.abs(curR - r) + Math.abs(curC - c);
        return left <= k && (k - left) % 2 == 0;
    }

    public boolean isInRange(int row, int col) {
        return 1 <= row && row < map.length && 1 <= col && col < map[0].length;
    }
}