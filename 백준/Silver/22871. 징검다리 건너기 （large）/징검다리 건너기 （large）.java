import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] rocks;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        rocks = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            rocks[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long findAnswer() {
        long left = 0;
        long right = 1_000_000L * (n - 1) * (1 + 1_000_000L);

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (can(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static boolean can(long K) {
        boolean[] canReach = new boolean[n];
        canReach[0] = true;

        for (int i = 0; i < n; i++) {
            if (!canReach[i]) continue;

            for (int j = i + 1; j < n; j++) {
                long need = (long)(j - i) * (1 + Math.abs(rocks[i] - rocks[j]));
                if (need <= K) {
                    canReach[j] = true;
                }
            }
        }

        return !canReach[n - 1];
    }
}