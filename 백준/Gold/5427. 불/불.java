import java.io.*;
import java.util.*;

public class Main {

    private static int testcase;
    private static char[][] map;
    private static int w, h;
    private static int[] start;
    private static Deque<int[]> fire;
    private static final int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static boolean[][] fv;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < testcase; i++) {
            init(input);
            int time = findAnswer();
            if (time == -1) {
                answer.append("IMPOSSIBLE").append("\n");
                continue;
            }
            answer.append(time).append("\n");
        }

        System.out.println(answer);
    }

    private static int findAnswer() {
        // 상근이가 나갈 수 있으면 종료 => 나가다의 의미는 상근이의 r or c가 벽에, w - 1 || h - 1위치에 있을것
        // 불은 매초마다 번짐
        // 상근이가 불, 벽, 지나온 곳에 둘려쌓이면 탈출 불가능 -> IMPOSSIBLE
        // 불번짐과 상근의 이동은 같은 시간 -> 같은 와일문에 별개의 큐
        // 불이 번짐에 따라서 상근이 이동할 수 없으면 그냥 그대로
        // 매 순차마다 상근이 도착했는지 여부를 체크하고 time 갱신
        // time 갱신이 이뤄지지 않았으면 IMPOSSIBLE
        Deque<int[]> sangeun = new ArrayDeque<>();
        sangeun.add(new int[]{start[0], start[1], 0});
        boolean[][] fv = new boolean[h][w];
        boolean[][] sv = new boolean[h][w];
        sv[start[0]][start[1]] = true;

        // 모든 지역이 끝나도 상근은 움직여야지
        while (!sangeun.isEmpty()) {
            boolean flag = false;
            // 불부터 번지게 하자
            int count = fire.size();
            while (count-- > 0) {
                int[] curFire = fire.poll();
                int cfr = curFire[0];
                int cfc = curFire[1];
                for (int[] dir : direction) {
                    int nextR = cfr + dir[0];
                    int nextC = cfc + dir[1];
                    if (isInRange(nextR, nextC) && !fv[nextR][nextC] && map[nextR][nextC] != '#') {
                        fire.offer(new int[]{nextR, nextC});
                        fv[nextR][nextC] = true;
                        map[nextR][nextC] = '*';
                    }
                }
            }
            // 상근의 이동
            count = sangeun.size();
            while (count-- > 0) {
                int[] curSang = sangeun.poll();
                int csr = curSang[0];
                int csc = curSang[1];
                int time = curSang[2];
                if (csr == h - 1 || csc == w - 1 || csr == 0 || csc == 0) {
                    return time + 1;
                }
                for (int[] dir : direction) {
                    int nextR = csr + dir[0];
                    int nextC = csc + dir[1];
                    if (isInRange(nextR, nextC) && !sv[nextR][nextC] && map[nextR][nextC] == '.') {
                        sangeun.offer(new int[]{nextR, nextC, time + 1});
                        sv[nextR][nextC] = true;
                    }
                }
            }
            if (flag) {
                break;
            }
        }

        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        w = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());
        map = new char[h][w];
        fv = new boolean[h][w];
        fire = new ArrayDeque<>();
        start = new int[2];
        for (int i = 0; i < h; i++) {
            String line = input.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '@') {
                    start = new int[]{i, j};
                    map[i][j] = '.';
                }

                if (map[i][j] == '*') {
                    fire.offer(new int[]{i, j});
                    fv[i][j] = true;
                }
            }
        }
    }
}