import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int h;
    private static int[][] map;
    private static List<int[]> milks;
    private static int maxMilkCount = Integer.MIN_VALUE;
    private static int[] start;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();

        System.out.println(maxMilkCount);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        milks = new ArrayList<>();
        start = new int[2];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                }
                if (map[i][j] == 2) {
                    milks.add(new int[]{i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        // 우유를 먹으면 체력이 2가 차,
        // 갔다가 돌아와야하니까 현재 위치 - 우유 위치는 최소한
        int curR = start[0];
        int curC = start[1];
        boolean[] visited = new boolean[milks.size()];
        dfs(curR, curC, m, 0, visited);

    }

    private static void dfs(int curR, int curC, int curHp, int count, boolean[] visited) {
        int homeDistance = Math.abs(curR - start[0]) + Math.abs(curC - start[1]);

        // 집에 갈 수 있는 거리면 갱신만 해주고 종교조건 없이 조건에 맞지 않으면 자연스럽게 함수가 끝나게
        if (homeDistance <= curHp) {
            maxMilkCount = Math.max(maxMilkCount, count);
        }

        for (int i = 0; i < milks.size(); i++) {
            if (visited[i]) {
                continue;
            }

            int nextR = milks.get(i)[0];
            int nextC = milks.get(i)[1];
            int distance = Math.abs(curR - nextR) + Math.abs(curC - nextC);

            // 다음 밀크까지 거리가 현재 체력보다 크면 못감
            if (distance > curHp) {
                continue;
            }

            visited[i] = true;
            dfs(nextR, nextC, curHp - distance + h, count + 1, visited);
            visited[i] = false;
        }
    }
}