import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int w, h;
    private static char[][] map;
    private static int[] start;
    private static List<int[]> trash;
    private static int minMove;
    private static int[][] graph;
    private static int[] order;
    private static int[] fromStart;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String line;

        while (!(line = input.readLine()).equals("0 0")) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            w = Integer.parseInt(tokenizer.nextToken());
            h = Integer.parseInt(tokenizer.nextToken());

            trash = new ArrayList<>();
            map = new char[h][w];
            start = new int[2];
            for (int i = 0; i < h; i++) {
                String str = input.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') {
                        start[0] = i;
                        start[1] = j;
                    }
                    if (map[i][j] == '*') {
                        trash.add(new int[]{i, j});
                    }
                }
            }
            graph = new int[trash.size()][trash.size()];
            fromStart = new int[trash.size()];
            settingGraph();

            findAnswer(answer);
        }

        System.out.println(answer);
    }

    // 생각좀 하자
    // 처음에 거리 계산하고 재귀에서는 접근이 낫지 재귀에서 bfs를 돌리고 있냐
    // 그래야하는 경우도 있지만 아는 경우도 있다는걸 기억해라
    private static void settingGraph() {
        for (int i = 0; i < trash.size(); i++) {
            fromStart[i] = bfs(start, trash.get(i));
        }

        for (int i = 0; i < trash.size(); i++) {
            int[] from = trash.get(i);
            for (int j = 0; j < trash.size(); j++) {
                int[] to = trash.get(j);
                graph[i][j] = bfs(from, to);
            }
        }
    }

    private static void findAnswer(StringBuilder answer) {
        minMove = Integer.MAX_VALUE;
        order = new int[trash.size()];
        findMoveCount(0, new boolean[trash.size()]);

        answer.append(minMove).append("\n");
    }

    private static void findMoveCount(int idx, boolean[] visited) {
        if (idx == trash.size()) {
            // 더러운 칸을 10개 넘지 않으면 9! = 72 * 42 * 20 * 6 = 840 * 432 = 4만도 안되고
            // 여기서 bfs로 각 거리 구하기
            int sum = fromStart[order[0]];
            if (sum == -1) {
                minMove = -1;
                return;
            }
            int from = order[0];
            // 36만 *
            for (int i = 1; i < order.length; i++) {
                int move = graph[from][order[i]];
                from = order[i];
                if (move == -1) {
                    minMove = -1;
                    return;
                }

                sum += move;
            }
            minMove = Math.min(minMove, sum);
            return;
        }

        for (int i = 0; i < trash.size(); i++) {
            if (!visited[i]) {
                order[idx] = i;
                visited[i] = true;
                findMoveCount(idx + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static int bfs(int[] from, int[] to) {
        boolean[][] visited = new boolean[h][w];
        visited[from[0]][from[1]] = true;
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{from[0], from[1], 0});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curMove = cur[2];
            if (curR == to[0] && curC == to[1]) {
                return curMove;
            }
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 'x') {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC, curMove + 1});
                }
            }
        }

        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }
}