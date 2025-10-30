import java.util.*;

class Solution {

    private int startR, startC;
    private int n, m;
    private int[][] map;
    private int pair;
    private final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int minMove;
    private int[] per;
    private Map<Integer, List<int[]>> list;

    public int solution(int[][] board, int r, int c) {
        init(board, r, c);
        // 어떤 카드부터 없앨지 계산
        // 카드 픽 - 엔터
        // 다른 카드로 이동. 컨트롤 방향 or 방향
        // 카드 픽 - 엔터
        makePer(0, new boolean[pair], r, c);
        return minMove;
    }

    private void makePer(int idx, boolean[] visited, int r, int c) {
        if (idx == per.length) {
            // 1번 카드와 2번 카드 중에 뭐먼저 볼지 정해야함
            // 0 1 0 1 0 1 0 1 0 1 0 1 0 1과같이
            int[] start = new int[pair * 2];
            makePair(0, start, r, c);
            return;
        }

        for (int i = 1; i <= pair; i++) {
            if (!visited[i - 1]) {
                per[idx] = i;
                visited[i - 1] = true;
                makePer(idx + 1, visited, r, c);
                visited[i - 1] = false;
            }
        }
    }

    private void makePair(int idx, int[] start, int r, int c) {
        if (idx == pair * 2) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(map[i], 0, copy[i], 0, n);
            }

            int click = 0;
            int[] from = {r, c};
            for (int k = 0; k < pair; k++) {
                int cardVal = per[k];
                List<int[]> pos = list.get(cardVal);
                int[] p = pos.get(0);
                int[] q = pos.get(1);

                int firstIdx = start[2 * k];
                int secondIdx = 1 - firstIdx;
                int[] first = (firstIdx == 0) ? p : q;
                int[] second = (secondIdx == 0) ? p : q;

                int d1 = bfs(from, first, copy);
                click += d1 + 1;
                from = new int[]{first[0], first[1]};

                int d2 = bfs(from, second, copy);
                click += d2 + 1;
                from = new int[]{second[0], second[1]};

                copy[p[0]][p[1]] = 0;
                copy[q[0]][q[1]] = 0;
            }
            minMove = Math.min(minMove, click);
            return;
        }
        if (idx % 2 == 1) {
            start[idx] = 1 - start[idx - 1];
            makePair(idx + 1, start, r, c);
            return;
        }

        for (int i = 0; i < 2; i++) {
            start[idx] = i;
            makePair(idx + 1, start, r, c);
        }
    }

    private int bfs(int[] from, int[] to, int[][] map) {
        if (from[0] == to[0] && from[1] == to[1]) {
            return 0;
        }
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        deque.offer(new int[]{from[0], from[1], 0});
        while (!deque.isEmpty()) {
            int[] poll = deque.poll();
            int curR = poll[0];
            int curC = poll[1];
            int count = poll[2];
            if (curR == to[0] && curC == to[1]) {
                return count;
            }
            for (int[] dir : direction) {
                int newR = curR + dir[0];
                int newC = curC + dir[1];
                if (isInRange(newR, newC) && !visited[newR][newC]) {
                    visited[newR][newC] = true;
                    deque.offer(new int[]{newR, newC, count + 1});
                }
            }
            for (int[] dir : direction) {
                int r = curR;
                int c = curC;
                while (true) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    if (!isInRange(nextR, nextC)) {
                        break;
                    }
                    r = nextR;
                    c = nextC;

                    if (map[nextR][nextC] != 0) break;
                }
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    deque.offer(new int[]{r, c, count + 1});
                }
            }
        }

        return -1;
    }

    private boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private void init(int[][] board, int r, int c) {
        minMove = Integer.MAX_VALUE;
        map = board;
        startR = r;
        startC = c;
        n = map.length;
        m = map[0].length;
        list = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;

                list.putIfAbsent(map[i][j], new ArrayList<>());
                list.get(map[i][j]).add(new int[]{i, j});
            }
        }

        pair = list.size();
        per = new int[pair];
    }
}