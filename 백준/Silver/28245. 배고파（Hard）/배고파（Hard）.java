import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static long[] num;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new  BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine().trim());
        num = new long[n];
        for (int i = 0; i < n; i++) {
            num[i] = Long.parseLong(input.readLine().trim());
        }
    }

    private static void findAnswer() {
        final long LIMIT = 2_300_000_000_000_000_000L;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long m = num[i];
            long min = Long.MAX_VALUE;
            int[] answer = new int[2];

            int xI = 0;
            boolean done = false;

            long xVal = 1L; 
            for ( ; xVal > 0 && xVal <= LIMIT; xVal*=2) {
                int yJ = 0;
                long yVal = 1L;
                for ( ; yVal > 0 && yVal <= LIMIT; yVal*=2) {
                    long sum = xVal + yVal;
                    long target = Math.abs(sum - m);
                    if (target < min) {
                        min = target;
                        answer[0] = xI;
                        answer[1] = yJ;
                        if (target == 0) { done = true; break; }
                    }
                    yJ++;
                    if (yVal > LIMIT / 2) break;
                }
                if (done) break;
                xI++;
                if (xVal > LIMIT / 2) break;
            }

            builder.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }

        System.out.print(builder);
    }
}