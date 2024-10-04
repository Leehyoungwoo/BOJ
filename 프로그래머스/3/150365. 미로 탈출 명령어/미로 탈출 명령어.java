import java.util.*;

class Solution {

    // D, L, R, U 순서
    private final int[][] direction = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private char[][] map;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        map = new char[n + 1][m + 1];
        map[x][y] = 'S';
        map[r][c] = 'E';
        answer = findAnwer(x, y, r, c, k, "");
        return answer.equals("") ? "impossible" : answer;
    }

    private String findAnwer(int curR, int curC, int r, int c, int k, String way) {
        if (curR == r && curC == c) {
            if (k == 0) {
                return way;
            }
        }

        if (!remainWay(curR, curC, r, c, k)) {
            return "";
        }

        if (k <= 0) {
            return "";
        }
        for (int i = 0; i < direction.length; i++) {
            int[] dir = direction[i];
            int nextR = curR + dir[0];
            int nextC = curC + dir[1];
            if (isInRange(nextR, nextC)) {
                String result = findAnwer(nextR, nextC, r, c, k - 1, way + decideDirection(i));
                if (!result.equals("")) {
                    return result;
                }
            }
        }
        return "";
    }

    private boolean remainWay(int curR, int curC, int r, int c, int k) {
        int left = Math.abs(curR - r) + Math.abs(curC - c);
        return left <= k && (k - left) % 2 == 0;
    }

    private String decideDirection(int i) {
        switch (i) {
            case 0:
                return "d"; // 아래
            case 1:
                return "l"; // 왼쪽
            case 2:
                return "r"; // 오른쪽
            case 3:
                return "u"; // 위
            default:
                return "";
        }
    }

    private boolean isInRange(int row, int col) {
        return 1 <= row && row <= map.length - 1 && 1 <= col && col <= map[0].length - 1;
    }
}