import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r;
    int c;

    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    private static int n;
    private static int m;
    private static int v;
    private static int[][] map;
    private static Queue<Pair> que = new LinkedList<>();
    private static StringBuilder answer = new StringBuilder();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        dfs(v, new boolean[n + 1]);
        answer.append('\n');
        push(v, v);
        bfs();
        System.out.println(answer);
    }

    private static void push(int row, int col) {
        answer.append(col).append(' ');
        que.offer(new Pair(row, col));
        visited[col] = true;
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int curR = cur.r;

            for (int i = 1; i < n + 1; i++) {
                if (map[curR][i] == 1 && !visited[i] && isInRange(i)) {
                    push(i, i);
                }
            }
        }
    }

    private static boolean isInRange(int nextCol) {
       return 1 <= nextCol && nextCol < n + 1;
    }

    private static void dfs(int cur, boolean[] visited) {
        answer.append(cur).append(' ');
        visited[cur] = true;

        for (int i = 1; i <= n; i++) {
            if (map[cur][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i, visited);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        v = Integer.parseInt(tokenizer.nextToken());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer2.nextToken());
            int end = Integer.parseInt(tokenizer2.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }
    }
}