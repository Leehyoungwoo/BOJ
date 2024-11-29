import java.io.*;

public class Main {
    private static final int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 좌, 하, 우, 상
    private static int n, target;
    private static int[][] arr;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        target = Integer.parseInt(input.readLine());
        arr = new int[n][n];

        fillArr();
        printArr();
        findTargetRC();
    }

    private static void fillArr() {
        int r = 0, c = 0, d = 0;
        int num = n * n;
        while (num > 0) {
            arr[r][c] = num--;
            int nr = r + direction[d][0];
            int nc = c + direction[d][1];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n || arr[nr][nc] != 0) {
                d = (d + 1) % 4;
                nr = r + direction[d][0];
                nc = c + direction[d][1];
            }
            r = nr;
            c = nc;
        }
    }

    private static void findTargetRC() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == target) {
                    System.out.println((i + 1) + " " + (j + 1)); 
                    return;
                }
            }
        }
    }

    private static void printArr() {
        for (int[] row : arr) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}