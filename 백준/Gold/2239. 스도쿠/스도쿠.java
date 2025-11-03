import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        fillSudoku(0);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String str = input.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
    }

    private static void fillSudoku(int idx) {
        if (idx == list.size()) {
            printAnswer();
            System.exit(0);
        }

        int row = list.get(idx)[0];
        int col = list.get(idx)[1];

        boolean[] check = new boolean[10];
        checkRow(row, check);
        checkCol(col, check);
        checkBox(row, col, check);

        for (int i = 1; i <= 9; i++) {
            if (!check[i]) {
                map[row][col] = i;
                fillSudoku(idx + 1);
                map[row][col] = 0;
            }
        }
    }

    private static void checkRow(int row, boolean[] check) {
        for (int i = 1; i <= 9; i++) {
            if (map[row][i - 1] != 0) {
                check[map[row][i - 1]] = true;
            }
        }
    }

    private static void checkCol(int col, boolean[] check) {
        for (int i = 1; i <= 9; i++) {
            if (map[i - 1][col] != 0) {
                check[map[i - 1][col]] = true;
            }
        }
    }

    private static void checkBox(int row, int col, boolean[] check) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (map[i][j] != 0) {
                    check[map[i][j]] = true;
                }
            }
        }

    }

    private static void printAnswer() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}