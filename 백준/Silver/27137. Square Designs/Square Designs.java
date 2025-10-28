import java.io.*;
import java.util.*;

public class Main {

    private static int m, n;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());

        int rows = Math.max(m, n) + 2;
        int leftWidth  = 2 * m + 3;   // + ---...--- +
        int rightWidth = 2 * n + 3;
        int gap = 10;
        int cols = leftWidth + gap + rightWidth;

        map = new char[rows][cols];
        for (int i = 0; i < rows; i++) Arrays.fill(map[i], ' ');

        fillBox(0, 0, m, rows, cols);
        fillBox(0, leftWidth + gap, n, rows, cols);

        String out = buildToString(rows, cols);
        System.out.print(out);
    }

    private static String buildToString(int rows, int cols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            int last = cols - 1;
            while (last >= 0 && map[i][last] == ' ') last--; // 트레일링 스페이스 제거

            // 정상적으로 출력될 문자가 하나도 없으면 (안 생기는 경우지만 안전하게)
            if (last < 0) {
                if (i < rows - 1) sb.append('\n');
                continue;
            }

            for (int j = 0; j <= last; j++) sb.append(map[i][j]);
            if (i < rows - 1) sb.append('\n');
        }
        return sb.toString();
    }

    private static void fillBox(int r, int c, int num, int rows, int cols) {
        // 윗변
        drawEdge(r, c, num);

        int innerLen = num * 2 + 1;
        for (int i = 1; i <= num; i++) {
            map[r + i][c] = 'I';
            for (int j = 1; j <= innerLen; j++) {
                map[r + i][c + j] = (j % 2 == 1) ? '-' : 'X';
            }
            map[r + i][c + innerLen + 1] = 'I';
        }

        // 아랫변
        drawEdge(r + num + 1, c, num);
    }

    private static void drawEdge(int r, int c, int num) {
        int innerLen = num * 2 + 1;
        map[r][c] = '+';
        for (int i = 1; i <= innerLen; i++) map[r][c + i] = '-';
        map[r][c + innerLen + 1] = '+';
    }
}