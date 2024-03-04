import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Node implements Comparable<Node> {
    int r;
    int c;
    int speed;
    int day;

    public Node(int r, int c, int speed, int day) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.day = day;
    }

    @Override
    public int compareTo(Node o) {
        if (this.day != o.day) {
            return this.day - o.day;  // day가 빠른 순으로 정렬
        } else if (this.speed != o.speed) {
            return o.speed - this.speed;  // speed가 높은 순으로 정렬
        } else if (this.r != o.r) {
            return this.r - o.r;  // r이 작은 순으로 정렬
        } else {
            return this.c - o.c;  // c가 작은 순으로 정렬
        }
    }
}
public class Main {

    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
    private static int m;
    private static int n;
    private static int[][] map;
    private static boolean[][] visited;
    private static PriorityQueue<Node> que = new PriorityQueue<>();
    private static int day = 0;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(day);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        m = Integer.parseInt(s[0]);
        n = Integer.parseInt(s[1]);
        visited = new boolean[m][n];
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String str = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] != 0) {
                    que.offer(new Node(i, j, map[i][j], 0));
                }
            }
        }
    }

    private static int findMessCount() {
        int cnt = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                cnt += findCount(i, j, visited);
            }
        }
        return cnt;
    }

    private static int findCount(int r, int c, boolean[][] visited) {
        Queue<int[]> queForCnt = new LinkedList<>();
        queForCnt.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!queForCnt.isEmpty()) {
            int[] go = queForCnt.poll();
            int curR = go[0];
            int curC = go[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 0) {
                    visited[nextR][nextC] = true;
                    queForCnt.offer(new int[]{nextR, nextC});
                }
            }
        }

        return 1;
    }

    private static void findAnswer() {
        if(findMessCount() == 1) {
            return;
        }
        int nowDay = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (map[cur.r][cur.c] > cur.speed) {  // 곰팡이가 확장할 수 없는 경우 무시
                continue;
            }
            int curR = cur.r;
            int curC = cur.c;
            int curDay = cur.day;
            if (nowDay < curDay) {
                int group = findMessCount();
                nowDay++;
                if (group == 1) {
                    day = nowDay;
                    return;
                }
            }
            for (int i = -cur.speed; i <= cur.speed; i++) {
                for (int j = -cur.speed; j <= cur.speed; j++) {
                    int nextR = curR + i;
                    int nextC = curC + j;
                    if (isInRange(nextR, nextC) && !visited[nextR][nextC] && cur.speed >= map[nextR][nextC]) {
                        map[nextR][nextC] = Math.max(cur.speed, map[nextR][nextC]);                        visited[nextR][nextC] = true;
                        Node node = new Node(nextR, nextC, cur.speed, curDay + 1);
                        que.offer(node);
                    }
                }
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}