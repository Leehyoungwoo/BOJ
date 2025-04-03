import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static char[][] map;
    private static List<int[]> list;
    private static List<int[]> teachers;
    private static boolean isAllHide;
    private static List<Integer> comb;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new char[n][n];
        list = new ArrayList<>();
        teachers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = line[j].charAt(0);
                if (map[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                }
                if (map[i][j] == 'X') {
                    list.add(new int[]{i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        comb = new ArrayList<>();
        // dfs로 약 36개 미만의 좌표에서 3개의 장애물을 놓을 좌표를 선택 (조합)-> 최악의 경우에도 6천회를 넘지 않음
        findComb(0, 0);

        // 한명의 학생도 걸리지 않으면 flag를 true로 변경 yes, 아닌 경우에는 No
        if (isAllHide) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }

    private static void findComb(int idx, int start) {
        // 각 조합이 완성될 때마다 선생의 시야에서 직선으로 쭉가서 걸리는 학생이 있는지 확인
        // 선생 리스트를 순회하면서 걸리는 학생이 있는지 확인
        if (idx == 3) {
            char[][] newMap = makeNewMap();

            // 고른 장애물 설치
            for (Integer i : comb) {
                int[] cur = list.get(i);
                newMap[cur[0]][cur[1]] = 'O';
            }

            if (isSafe(newMap)) {
                isAllHide = true;
            }

            return;
        }

        if (start == list.size()) {
            return;
        }

        comb.add(start);
        findComb(idx + 1, start + 1);
        comb.remove(comb.size() - 1);
        findComb(idx, start + 1);
    }

    private static boolean isSafe(char[][] newMap) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int[] teacher : teachers) {
            for (int d = 0; d < 4; d++) {
                int r = teacher[0];
                int c = teacher[1];
                while (true) {
                    r += dr[d];
                    c += dc[d];
                    if (!isInRange(r, c)) break;
                    if (newMap[r][c] == 'O') break;
                    if (newMap[r][c] == 'S') return false; // 한 명이라도 걸리면 탈락
                }
            }
        }
        
        return true; // 모든 선생이 감지 못함
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    private static char[][] makeNewMap() {
        char[][] newMap = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        return newMap;
    }
}
