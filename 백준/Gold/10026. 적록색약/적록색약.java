import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] dr = { -1, 0, 1, 0 };
    private static int[] dc = { 0, 1, 0, -1 };

    private static int n;
    private static char[][] normalMap;
    private static char[][] jaejoonMap;
    private static int normalCnt = 0;
    private static int jaejoonCnt = 0;
    private static boolean[][] jaejoonVisited;
    private static boolean[][] normalVisited;

    public static void main(String[] args) throws IOException {
        init();
        findNormalCount();
        findJaeJoonCount();
        System.out.println(normalCnt + " " + jaejoonCnt);
    }

    private static void findJaeJoonCount() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!jaejoonVisited[i][j]) {
                    jaejoonDfs(i, j, jaejoonMap[i][j]);
                    jaejoonCnt++;
                }
            }
        }
    }

    private static void findNormalCount() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!normalVisited[i][j]) {
                    normalDfs(i, j, normalMap[i][j]);
                    normalCnt++;
                }
            }
        }
    }

    private static void jaejoonDfs(int r, int c, char color) {
        jaejoonVisited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if(isInRange(nextR, nextC) && !jaejoonVisited[nextR][nextC]
                    && jaejoonMap[nextR][nextC] == color) {
                jaejoonDfs(nextR, nextC, color);
            }
        }
    }

    private static void normalDfs(int r, int c, char color) {
        normalVisited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if(isInRange(nextR, nextC) && !normalVisited[nextR][nextC]
                    && normalMap[nextR][nextC] == color) {
                normalDfs(nextR, nextC, color);
            }
        }
    }


    private static boolean isInRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        normalMap = new char[n][n];
        jaejoonMap = new char[n][n];
        normalVisited = new boolean[n][n];
        jaejoonVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = input.readLine();
            for (int j = 0; j < n; j++) {
                normalMap[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (normalMap[i][j] == 'R') {
                   jaejoonMap[i][j] = 'G';
                   continue;
                }
                jaejoonMap[i][j] = normalMap[i][j];
            }
        }
    }
}