import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static long m;
    private static long[] times;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Long.parseLong(tokenizer.nextToken());
        times = new long[n];
        for (int i = 0; i < n; i++) {
            times[i] = Long.parseLong(input.readLine());
        }
    }

    private static void findAnswer() {
        long left = 1;
        long right = (long) Arrays.stream(times).min().getAsLong() * m;
        long answer = right;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canProcessAll(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean canProcessAll(long totaltime) {
        long sum = 0;
        for(long time : times) {
            sum += totaltime / time;
            if (sum >= m) {
                return true;
            }
        }
        return sum >= m;
    }
}