import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] bad;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bad = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int pos = Integer.parseInt(in.readLine());
            bad[pos] = true;
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int maxSpeed = (int)Math.sqrt(2 * N) + 2;
        visited = new boolean[N + 1][maxSpeed];

        Queue<int[]> queue = new LinkedList<>();
        int startPos = 2;
        int startSpeed = 1;
        int startJumps = 1;

        if (startPos <= N && !bad[startPos]) {
            visited[startPos][startSpeed] = true;
            queue.offer(new int[] { startPos, startSpeed, startJumps });
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position   = current[0];
            int speed      = current[1];
            int jumps      = current[2];

            if (position == N) {
                return jumps;
            }

            for (int delta = -1; delta <= 1; delta++) {
                int nextSpeed = speed + delta;
                if (nextSpeed <= 0 || position + nextSpeed > N) {
                    continue;
                }

                int nextPos = position + nextSpeed;
                if (bad[nextPos]) {
                    continue;
                }

                if (visited[nextPos][nextSpeed]) {
                    continue;
                }

                visited[nextPos][nextSpeed] = true;
                queue.offer(new int[] { nextPos, nextSpeed, jumps + 1 });
            }
        }

        return -1;
    }
}