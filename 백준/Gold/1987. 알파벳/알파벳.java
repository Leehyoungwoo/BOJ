import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int R;
    private static int C;
    private static char[][] map;
    private static int[][] cache; // cache[i][j] := i행과 j열에서 출발할 때, 최대 거리가 되도록 하는 현재까지의 경로

    public static void main(String[] args) throws IOException {
        init();
        int answer = findLongestDistance(0, 0, 1 << (map[0][0] - 'A'));
        System.out.println(answer);
    }

    private static int findLongestDistance(int row, int col, int bitmask) {
        if (cache[row][col] == bitmask) {
            return Integer.MIN_VALUE;
        }

        int max = 1;

        for (int[] direction : DIRECTIONS) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (isPossibleToMove(nextRow, nextCol, bitmask)) {
                max = Math.max(
                        max,
                        findLongestDistance(nextRow, nextCol, bitmask | 1 << (map[nextRow][nextCol] - 'A')) + 1
                );
            }
        }

        cache[row][col] = bitmask;
        return max;
    }

    private static boolean isPossibleToMove(int nextRow, int nextCol, int bitmask) {
        return isInRange(nextRow, nextCol) && !isVisitedAlphabet(nextRow, nextCol, bitmask);
    }

    private static boolean isVisitedAlphabet(int nextRow, int nextCol, int bitmask) {
        return (bitmask >> (map[nextRow][nextCol] - 'A') & 1) == 1;
    }

    private static boolean isInRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = input.readLine().split(" ");
        R = Integer.parseInt(strings[0]);
        C = Integer.parseInt(strings[1]);
        map = new char[R][C];

        for (int i = 0; i < map.length; i++) {
            String s = input.readLine();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        cache = new int[R][C];
    }
}