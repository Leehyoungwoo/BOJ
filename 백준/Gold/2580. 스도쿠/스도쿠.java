import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int[][] map = new int[9][9];
    private static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        solve(0);
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < map.length; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
    }

    private static boolean solve(int idx) {
        if (idx == list.size()) {
            return true; // 모든 빈 칸을 채웠다면 성공
        }
        int[] cur = list.get(idx);
        int curR = cur[0];
        int curC = cur[1];
        for (int num = 1; num <= 9; num++) {
            if (isValid(curR, curC, num)) {
                map[curR][curC] = num;
                if (solve(idx + 1)) { // 다음 빈 칸으로 진행
                    return true; // 퍼즐이 완성된 경우
                }
                map[curR][curC] = 0; // 실패한 경우, 초기화
            }
        }
        return false; // 모든 숫자를 시도했으나 실패
    }

    private static boolean isValid(int r, int c, int num) {
        // 행 검사
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == num) {
                return false;
            }
        }
        // 열 검사
        for (int i = 0; i < 9; i++) {
            if (map[i][c] == num) {
                return false;
            }
        }
        // 3x3 서브그리드 검사
        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printAnswer() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}