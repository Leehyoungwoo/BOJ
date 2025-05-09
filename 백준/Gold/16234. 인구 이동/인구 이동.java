import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n, l, r;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        l = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findAnswer() {
        // 오늘부터 인구 이동이 시작되는 날이다.
        int day = 0;

        //인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
        while (isPossibleToMove()) {
            boolean[][] visited = new boolean[n][n];
            List<List<int[]>> country = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        findUnion(visited, country, i, j);
                        // 리스트에 연합 별로 담아주기
                        // 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
                        // 연합을 해체하고, 모든 국경선을 닫는다.
                        // 인구 며칠동안 했는지 출력
                    }
                }
            }
            // 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
            // 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
            // 리스트를 순회하면서 해당 리스트의 리스트 안에 있는 좌표의 map[i][j] 모두 더해주고 평균 나누기, 값을 소숫점 버려서 입력
            for (int i = 0; i < country.size(); i++) {
                List<int[]> con = country.get(i);
                int size = con.size();
                int sum = 0;
                for (int j = 0; j < con.size(); j++) {
                    int[] cur = con.get(j);
                    sum += map[cur[0]][cur[1]];
                }
                int avg = sum / size;
                for (int j = 0; j < con.size(); j++) {
                    int[] cur = con.get(j);
                    map[cur[0]][cur[1]] = avg;
                }
            }
            day++;
        }

        System.out.println(day);
    }

    private static boolean isPossibleToMove() {
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> country = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    findUnion(visited, country, i, j);
                }
            }
        }
        for (int i = 0; i < country.size(); i++) {
            List<int[]> con = country.get(i);
            if (con.size() != 1) {
                return true;
            }
        }

        return false;
    }

    private static void findUnion(boolean[][] visited, List<List<int[]>> country, int r, int c) {
        // 해당 열을 열어서 차이가 l, r 사이면 bfs로 연합을 넓혀가면서 visited 체크
        Queue<int[]> que = new LinkedList<>();
        List<int[]> union = new ArrayList<>();
        que.offer(new int[]{r, c});
        visited[r][c] = true;
        union.add(new int[]{r, c});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                // 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && isPossibleToUnion(map[curR][curC], map[nextR][nextC])) {
                    que.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    union.add(new int[]{nextR, nextC});
                }
            }
        }
        country.add(new ArrayList<>(union));
    }

    private static boolean isPossibleToUnion(int cur, int next) {
        int diff = Math.abs(cur - next);
        return l <= diff && diff <= r;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}