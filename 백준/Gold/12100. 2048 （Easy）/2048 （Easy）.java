import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] map;
    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int maxValue = Integer.MIN_VALUE;
    private static Map<Integer, Set<Integer>> merged;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        merged = new HashMap<>();
    }

    private static void findAnswer() {
        // 다시해보자
        // 머지 상태 관리, 이전 위치 관리. 근데 만약에 grid를 매시도 복사해서 사용하면 가능할거 같은데
        dfs(0, map);
        System.out.println(maxValue);
    }

    private static void dfs(int round, int[][] map) {
        if (round == 5) {
            int max = findMax(map);
            maxValue = Math.max(maxValue, max);
            return;
        }

        // 백트래킹용
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            // 무브 로직에서 map을 실제로 이동하면서 병합 여부도 기록해야하함
            Set<Integer> roundSet = new HashSet<>();
            move(i, map, round, roundSet);
            dfs(round + 1, map);
            backTracking(temp, map);
            merged.remove(round);
        }
    }

    private static void move(int dir, int[][] map, int round, Set<Integer> roundSet) {
        if (dir == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    step(i, j, dir, map, roundSet);
                }
            }
        } else if (dir == 0) {
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    step(i, j, dir, map, roundSet);
                }
            }
        } else if (dir == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    step(i, j, dir, map, roundSet);
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    step(i, j, dir, map, roundSet);
                }
            }
        }
        merged.put(round, roundSet);
    }

    private static void step(int i, int j, int dir, int[][] map, Set<Integer> roundSet) {
        if (map[i][j] == 0) return;
        if (isInSide(i, j, dir)) return;

        int r = i;
        int c = j;
        while (true) {
            int nextR = r + direction[dir][0];
            int nextC = c + direction[dir][1];
            if (!isInRange(nextR, nextC)) break;

            if (map[nextR][nextC] == 0) {
                // 빈 칸으로 '대입' 이동 (순서 중요)
                map[nextR][nextC] = map[r][c];
                map[r][c] = 0;
                r = nextR;
                c = nextC;
            } else {
                if (map[r][c] != map[nextR][nextC]) break;

                if (roundSet.contains(makeIdx(nextR, nextC))) break;

                map[nextR][nextC] += map[r][c];
                map[r][c] = 0;
                roundSet.add(makeIdx(nextR, nextC));
                break;
            }
        }
    }

    private static int makeIdx(int r, int c) {
        return r * n + c;
    }

    private static boolean isInSide(int r, int c, int dir) {
        // {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
        if (r == n - 1 && dir == 0) return true;

        if (r == 0 && dir == 1) return true;

        if (c == n - 1 && dir == 2) return true;

        if (c == 0 && dir == 3) return true;

        return false;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    private static void backTracking(int[][] temp, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static int findMax(int[][] map) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }
}