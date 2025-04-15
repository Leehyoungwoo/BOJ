import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int robotR, robotC, d;
    private static int[][] map;
    // 북 동 남 서
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        robotR = Integer.parseInt(tokenizer.nextToken());
        robotC = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findAnswer() {
        // 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
        boolean[][] isCleaned = new boolean[n][m];
        while (true) {
            //현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (map[robotR][robotC] == 0 && !isCleaned[robotR][robotC]) {
                isCleaned[robotR][robotC] = true;
            }

            //현재 칸의 주변 칸 중 청소되지 않은 빈 칸이 없는 경우,
            if (!isAllCanClean(isCleaned)) {
                //바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                int newD = (d + 2) % 4;
                int newR = direction[newD][0] + robotR;
                int newC = direction[newD][1] + robotC;
                if (isInRange(newR, newC) && map[newR][newC] == 0) {
                    robotR = newR;
                    robotC = newC;
                } else {
                    //바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                    break;
                }
            } else {
                //현재 칸의 주변 칸 중 청소되지 않은 빈 칸이 있는 경우,
                //반시계 방향으로 90도 회전
                d = (d + 3) % 4;
                //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                int newR = direction[d][0] + robotR;
                int newC = direction[d][1] + robotC;
                if (isInRange(newR, newC) && map[newR][newC] == 0 && !isCleaned[newR][newC]) {
                    robotR = newR;
                    robotC = newC;
                }
                // 1번으로 돌아간다.
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isCleaned[i][j]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isAllCanClean(boolean[][] isCleaned) {
        for (int[] dir : direction) {
            int nextR = robotR + dir[0];
            int nextC = robotC + dir[1];
            if (isInRange(nextR, nextC) && map[nextR][nextC] != 1 &&!isCleaned[nextR][nextC]) {
                return true;
            }
        }

        return false;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
