import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] map;
    private static BabyShark babyShark;
    private static final int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; 

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findAnswer());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    babyShark = new BabyShark(i, j);
                    map[i][j] = 0; // 상어 위치 초기화
                }
            }
        }
    }

    private static int findAnswer() {
        int totalTime = 0;
        
        while (true) {
            Fish targetFish = bfs(); 

            if (targetFish == null) break;

            babyShark.r = targetFish.r;
            babyShark.c = targetFish.c;
            babyShark.eat();
            totalTime += targetFish.dist;
            map[targetFish.r][targetFish.c] = 0; 
        }

        return totalTime;
    }

    private static Fish bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        queue.add(new int[]{babyShark.r, babyShark.c, 0});
        visited[babyShark.r][babyShark.c] = true;

        PriorityQueue<Fish> pq = new PriorityQueue<>(); 

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            if (map[r][c] > 0 && map[r][c] < babyShark.size) {
                pq.add(new Fish(r, c, dist));
            }

            for (int[] d : direction) {
                int nr = r + d[0], nc = c + d[1];
                if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] <= babyShark.size) {
                    queue.add(new int[]{nr, nc, dist + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll(); 
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}

class BabyShark {
    int r, c, size, eatCount;

    public BabyShark(int r, int c) {
        this.r = r;
        this.c = c;
        this.size = 2;
        this.eatCount = 0;
    }

    public void eat() {
        eatCount++;
        if (eatCount == size) {
            size++;
            eatCount = 0;
        }
    }
}

class Fish implements Comparable<Fish> {
    int r, c, dist;

    public Fish(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }

    @Override
    public int compareTo(Fish o) {
        if (this.dist == o.dist) {
            if (this.r == o.r) return this.c - o.c;
            return this.r - o.r;
        }
        return this.dist - o.dist;
    }
}
