import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Integer s;
    private static Integer N;
    private static Integer K;
    private static Integer R1;
    private static Integer R2;
    private static Integer C1;
    private static Integer C2;
    private static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] read = input.readLine().split(" ");
        s = Integer.parseInt(read[0]);
        N = Integer.parseInt(read[1]);
        K = Integer.parseInt(read[2]);
        R1 = Integer.parseInt(read[3]);
        R2 = Integer.parseInt(read[4]);
        C1 = Integer.parseInt(read[5]);
        C2 = Integer.parseInt(read[6]);
        arr = new char[51][51];
    }

    private static void findAnswer() {
        divide(0, 0, (int) Math.pow(N, s), false);
    }

    private static void divide(int x, int y, int size, boolean isBlack) {
        if (x > C2 || x + size <= C1 || y > R2 || y + size <= R1) {
            return;
        }
        if (size == 1) {
            arr[y - R1][x - C1] = isBlack ? '1' : '0';
            return;
        }
        int nSize = size / N;
        int blackStart = (N - K) / 2;
        int blackEnd = N - blackStart;
        for (int i = 0; i < N; i++) {
            int nY = y + nSize * i;
            for (int j = 0; j < N; j++) {
                int nX = x + nSize * j;
                divide(nX, nY, nSize, isBlack || (i >= blackStart && i < blackEnd) && (j >= blackStart && j < blackEnd));
            }
        }
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= R2 - R1; i++) {
            for (int j = 0; j <= C2 - C1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}