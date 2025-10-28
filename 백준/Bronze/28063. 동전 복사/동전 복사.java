import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] start;
    private static int[][] map;
    private static int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        start = new int[2];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        start[0] = Integer.parseInt(tokenizer.nextToken());
        start[1] = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][n];
        map[start[0] - 1][start[1] - 1] = 1;
    }

    private static void findAnswer() {
        // 시작점에서 기계가 방향을 입력하면 현재 동전이 있는 위치에서 그 방향쪽으로 동전을 다 채우고
        // 평소와 다르게  map에서 위쪽은 1, 0
        // 근데 생각해보면 시작 위치가 꼭지점이면 2,(위옆으로 가면 가득 채워지니까) 모서리면 3(모서리 끝쪽으로 하고 위쪽으로 올리면 끝.) 나머지면 4아님?
        // 만약에 이미 다 차있으면 return 0
        if (allreadyFull(map)) {
            System.out.println(0);
            return;
        }


        if (isPoint(start)) {
            System.out.println(2);
            return;
        }

        if (isSide(start)) {
            System.out.println(3);
            return;
        }

        System.out.println(4);
    }

    private static boolean allreadyFull(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSide(int[] start) {
        int r = start[0] - 1;
        int c = start[1] - 1;
        return ((r == 0 || r == n - 1) && (0 < c && c < n - 1)) || ((c == 0 || c == n - 1) && (0 < r && r < n - 1));
    }

    private static boolean isPoint(int[] start) {
        int r = start[0] - 1;
        int c = start[1] - 1;
        return (r == 0 && c == 0) || (r == n - 1 && c == 0) || (r == 0 && c == n - 1) || (r == n - 1 && c == n - 1);
    }
}