import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static List<int[]> apple;
    private static int[][] map;
    private static int l;
    private static Queue<String> changeDirection;
    private static boolean[][] isApple;

    // 동 북 서 남
    private static int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        // 사과 기록
        apple = new ArrayList<>();
        k = Integer.parseInt(input.readLine());
        isApple = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken()) - 1;
            isApple[r][c] = true;
        }
        changeDirection = new LinkedList<>();
        l = Integer.parseInt(input.readLine());
        for (int i = 0; i < l; i++) {
            changeDirection.add(input.readLine());
        }
    }

    private static void findAnswer() {
        // 뱀의 처음 위치는 0, 0, 뱀은 오른쪽을 바라봄
        int headR = 0;
        int headC = 0;
        int snakeD = 0;
        int time = 0;
        List<int[]> snakeWay = new ArrayList<>();
        snakeWay.add(new int[]{headR, headC});
        int snakeIndex = 0;

        // 뱀의 몸통은 2로 표시
        map[headR][headC] = 2;
        while (true) {
            time++;
            int nextR = headR + direction[snakeD][0];
            int nextC = headC + direction[snakeD][1];

            // 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
            if (!isInRange(nextR, nextC) || map[nextR][nextC] == 2) {
                break;
            }
            // 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            map[nextR][nextC] = 2;
            headR = nextR;
            headC = nextC;
            snakeWay.add(new int[]{nextR, nextC});

            // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
            if (!isApple[headR][headC]) {
                int[] cur = snakeWay.get(snakeIndex);
                map[cur[0]][cur[1]] = 0;
                snakeIndex++;
            } else {
                isApple[headR][headC] = false;
            }

            if (!changeDirection.isEmpty()) {
                String[] orders = changeDirection.peek().split(" ");
                if (time == Integer.parseInt(orders[0])) {
                    changeDirection.poll();
                    String dChange = orders[1];
                    if (dChange.equals("D")) {
                        //
                        snakeD = (snakeD + 3) % 4;
                    } else {
                        snakeD = (snakeD + 1) % 4;
                    }
                }
            }

//            System.out.println(time + "초의 맵 상황");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (isApple[i][j]) {
//                        System.out.print(9 + " ");
//                        continue;
//                    }
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
        }

        // 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
        System.out.println(time);
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
