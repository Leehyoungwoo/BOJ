import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int R;
    private static int[][] map;
    private static int[][] rotatedMap;


    public static void main(String[] args) throws IOException {
        init();
        rotateMapRTime();
        printMap();
    }

    private static void rotateMapRTime() {
        int layers = Math.min(N, M) / 2;

        for (int i = 0; i < layers; i++) {
            int element = 2 * (N - 2 * i + M - 2 * i) - 4;
            int rotation = R % element;

            for (int j = 0; j < rotation; j++) {
                rotateMap(i, element);
            }
        }
    }

    private static void rotateMap(int n, int element) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int curX = n;
        int curY = n;
        int cnt = 0;

        while (cnt != element) {
            cnt++;
            if (curY == n && (n <= (curX - 1) && (curX - 1) < M - n)) {
                int newX = curX+ dx[0];
                int newY = curY + dy[0];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[0];
                curY+=dy[0];
                continue;
            }

            if (curX == n && (n <= (curY + 1) && (curY + 1) < N - n)) {
                int newX = curX + dx[1];
                int newY = curY + dy[1];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[1];
                curY+=dy[1];
                continue;
            }

            if (curY == N - 1 - n && (n <= (curX + 1) && (curX + 1) < M - n)) {
                int newX = curX + dx[2];
                int newY = curY + dy[2];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[2];
                curY+=dy[2];
                continue;
            }

            if (curX == M - 1 - n && (n <= (curY - 1) && (curY - 1) < N - n)) {
                int newX = curX + dx[3];
                int newY = curY + dy[3];
                rotatedMap[newY][newX] = map[curY][curX];
                curX+=dx[3];
                curY+=dy[3];
            }
        }

        for (int i = n; i < map.length - n; i++) {
            for (int j = n; j < map[i].length - n; j++) {
                if (rotatedMap[i][j] == 0 || map[i][j] == rotatedMap[i][j]) {
                    continue;
                }
                map[i][j] = rotatedMap[i][j];
            }
        }
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        rotatedMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer underSt = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(underSt.nextToken());
            }
        }
    }
}