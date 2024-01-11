import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x;
    int y;
    int cnt;
    int kCnt;

    public Node(int x, int y, int cnt, int kCnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.kCnt = kCnt;
    }
}

public class Main {

    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    private static int k;
    private static int w;
    private static int h;
    private static int[][] map;
    private static int answer = Integer.MAX_VALUE;
    private static Queue<Node> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        w = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());

        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void bfs() {
        int[][] visited = new int[h][w];
        fillVisited(visited);
        Node start = new Node(0, 0, 0, k);
        que.offer(start);
        visited[0][0] = start.kCnt;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            int curX = cur.x;
            int curY = cur.y;

            if (cur.cnt + 1 > answer) {
                continue;
            }

            if (curX == h - 1 && curY == w - 1) {
                answer = Math.min(answer, cur.cnt);
            }
            for (int i = 0; i < direction.length; i++) {
                // k값이 더 없는데 말 이동 인덱스로 가면 pass
                if (i >= 4 && cur.kCnt == 0) {
                    continue;
                }
                int nextX = curX + direction[i][0];
                int nextY = curY + direction[i][1];

                if (isInRange(nextX, nextY) && map[nextX][nextY] == 0 && isValidToMove(i, nextX, nextY, cur, visited)) {

                    if (i >= 4) {
                        visited[nextX][nextY] = cur.kCnt - 1;
                        que.offer(new Node(nextX, nextY, cur.cnt + 1, cur.kCnt - 1));
                        continue;
                    }
                    visited[nextX][nextY] = cur.kCnt;
                    que.offer(new Node(nextX, nextY, cur.cnt + 1, cur.kCnt));
                }
            }
        }
    }

    private static void fillVisited(int[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], -1);
        }
    }

    private static boolean isValidToMove(int i,int nextX, int nextY, Node cur, int[][] visited) {
        if (visited[nextX][nextY] == -1) {
            return true;
        }

        if (cur.kCnt - 1 == visited[nextX][nextY]) {
            if(i >= 4) {
                return false;
            }
            return true;
        }

        if (cur.kCnt > visited[nextX][nextY]) {
            return true;
        }
        return false;
//        return visited[nextX][nextY] == -1 || cur.kCnt - 1 >= visited[nextX][nextY];
    }

    private static boolean isInRange(int nextX, int nextY) {
        return 0 <= nextX && nextX < h && 0 <= nextY && nextY < w;
    }

    private static void printAnswer() {
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }
}