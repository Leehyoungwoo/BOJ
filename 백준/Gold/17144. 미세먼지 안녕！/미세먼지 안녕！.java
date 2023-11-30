import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int r;
    int c;
    int w;

    public Node(int r, int c, int w) {
        this.r = r;
        this.c = c;
        this.w = w;
    }
}

public class Main {

    private static final int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int r;
    private static int c;
    private static int t;
    private static int[][] map;
    private static Queue<Node> dust;
    private static int airCleaner = -1;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        r = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        t = Integer.parseInt(tokenizer.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (airCleaner == -1 && map[i][j] == -1) {
                    airCleaner = i;
                }
            }
        }
    }

    private static void findAnswer() {
        for (int i = 0; i < t; i++) {
            //미세먼지 확인
            checkDust();
            // 미세먼지 확산
            dustSpread();
            // 공기청정기 작동
            airCleanerRunning();
        }
    }

    private static void checkDust() {
        dust = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1 || map[i][j] == 0) {
                    continue;
                }
                dust.offer(new Node(i, j, map[i][j]));
            }
        }
    }

    private static void dustSpread() {
        while (!dust.isEmpty()) {
            Node cur = dust.poll();
            if (cur.w < 5) {
                continue;
            }
            int spreadAmount = cur.w / 5;
            int cnt = 0;

            for (int[] dir : direction) {
                int nextR = cur.r + dir[0];
                int nextC = cur.c + dir[1];
                if (isInRange(nextR, nextC)) {
                    if (map[nextR][nextC] == -1) {
                        continue;
                    }
                    map[nextR][nextC] += spreadAmount;
                    cnt++;
                }
            }

            map[cur.r][cur.c] -= spreadAmount * cnt;
        }
    }

    private static void airCleanerRunning() {
        int top = airCleaner;
        int down = airCleaner + 1;

        // 위쪽 공기청정기 작동
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0;

        // 아래쪽 공기청정기 작동
        for (int i = down + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }
        for (int i = r - 1; i > down; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            map[down][i] = map[down][i - 1];
        }
        map[down][1] = 0;
    }

    private static boolean isInRange(int row, int col) {
        return 0 <= row && row < r && 0 <= col && col < c;
    }

    private static void printAnswer() {
        int leftDust = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1 || map[i][j] == 0) {
                    continue;
                }
                leftDust += map[i][j];
            }
        }

        System.out.println(leftDust);
    }
}