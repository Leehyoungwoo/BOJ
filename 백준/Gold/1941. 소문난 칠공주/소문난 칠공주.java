import java.io.*;
import java.util.*;

public class Main {

    private static final int n = 5;
    private static char[][] map;
    private static final int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int count;
    private static List<int[]> index;
    private static int[] comb;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        map = new char[n][n];
        index = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String line = input.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
                index.add(new int[]{i, j});
            }
        }
        comb = new int[7];
    }

    private static int findAnswer() {
        // 중복 체크가 중요한데 다른거보다
        // 우선 이다솜파와 임도연 파의 두가지 수를 같이 관리하다가 4명 이하로 떨어지면 바로 리턴하고 7명 꽉찼는데 조건을 맞췄다 -> 전역변수 증가
        // 7명 -> 전체는 25명이니까 25C7 = 25 23 22 2 19 = 38만 언더
        // dfs로 경로를 찾는 문제가 아니라 콤비네이션 문제네
        count = 0;
        makeCombination(0, 0);

        return count;
    }

    private static void makeCombination(int idx, int start) {
        if (idx == 7) {
            if (isSevenPrincess() && isLinked()) {
                count++;
            }
            return;
        }

        if (start == index.size()) return;

        comb[idx] = start;
        makeCombination(idx + 1, start + 1);
        makeCombination(idx, start + 1);
    }

    private static boolean isLinked() {
        boolean[][] selected = new boolean[7][7];
        for (int i = 0; i < comb.length; i++) {
            int r = index.get(comb[i])[0];
            int c = index.get(comb[i])[1];
            selected[r][c] = true;
        }
        Deque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        que.offer(new int[] {index.get(comb[0])[0], index.get(comb[0])[1]});
        int count = 0;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && selected[nextR][nextC]) {
                    count++;
                    visited[nextR][nextC] = true;
                    que.offer(new int[] {nextR, nextC});
                }
            }
        }

        return count == 7;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    private static boolean isSevenPrincess() {
        int dasom = 0;
        int doyun = 0;
        for (int i = 0; i < comb.length; i++) {
            int[] cur = index.get(comb[i]);
            if (map[cur[0]][cur[1]] == 'Y') {
                doyun++;
            } else {
                dasom++;
            }
        }

        return dasom >= 4;
    }
}