import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static Map<Integer, List<int[]>> index;
    private static List<int[]> camera;
    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int[] look;
    private static int minSafe;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][m];
        index = new HashMap<>();
        camera = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    camera.add(new int[]{map[i][j], i, j});
                }
            }
        }
        look = new int[camera.size()];
        // 카메라별 방향 세팅
        for (int i = 1; i <= 5; i++) {
            index.putIfAbsent(i, new ArrayList<>());
        }
        index.get(1).addAll(Arrays.asList(new int[]{0}, new int[]{1}, new int[]{2}, new int[]{3}));
        index.get(2).addAll(Arrays.asList(new int[]{0, 1}, new int[]{2, 3}));
        index.get(3).addAll(Arrays.asList(new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 2}, new int[]{1, 3}));
        index.get(4).addAll(Arrays.asList(new int[]{0, 1, 2}, new int[]{0, 1, 3}, new int[]{0, 2, 3}, new int[]{1, 2, 3}));
        index.get(5).addAll(Arrays.asList(new int[]{0, 1, 2, 3}));
    }

    // 단방향, 양방향, 90도, 삼방향, 사방향 감시 가능한 카메라가 있음
    // 카메라 인덱스는 각각  1 ~ 5
    // 6은 벽
    //CCTV는 CCTV를 통과 가능
    // 사각지대는?
    // 1번은 4방향, 2번은 두방향, 3번 네방향, 4번 두방향, 5번 1방향 나옴
    // 리스트에 감시카메라는 담아뒀으니까 방향만 정해서 배열에 담아두고
    // 마지막에 방향 결졍되었으면 리스트와 방향을 함께 순회하면서 감시구역 채우면 될듯?
    private static int findAnswer() {
        minSafe = Integer.MAX_VALUE;
        dfs(0);

        return minSafe;
    }

    private static void dfs(int idx) {
        if (idx == camera.size()) {
            int safeArea = countArea();
            minSafe = Math.min(minSafe, safeArea);
            return;
        }

        for (int i = 0; i < index.get(camera.get(idx)[0]).size(); i++) {
            look[idx] = i;
            dfs(idx + 1);
        }
    }

    private static int countArea() {
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < look.length; i++) {
            int cameraNumber = camera.get(i)[0];
            int[] dir = index.get(cameraNumber).get(look[i]);
            for (int d : dir) {
                int curR = camera.get(i)[1];
                int curC = camera.get(i)[2];
                while (true) {
                    int nextR = curR + direction[d][0];
                    int nextC = curC + direction[d][1];

                    if (!isInRange(nextR, nextC)) break;

                    if (map[nextR][nextC] == 6) break;

                    visited[nextR][nextC] = true;
                    curR = nextR;
                    curC = nextC;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    count++;
                }
            }
        }
//        if (count == 1) {
//            pringArr(visited);
//        }

        return count;
    }

    private static void pringArr(boolean[][] visited) {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    print.append("O" + " ");
                } else {
                    print.append("X" + " ");
                }
            }
            print.append("\n");
        }

        System.out.println(print);

    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}