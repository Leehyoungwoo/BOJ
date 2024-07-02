import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        long k = Long.parseLong(input.readLine());
        long left = 1;
        long right = n * (long)n;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (countLessOrEqual(mid, n) >= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static long countLessOrEqual(long mid, int n) {
        long cnt = 0; 
        for (int i = 1; i <= n; i++) {
            cnt += Math.min(mid / i, n);
        }
        return cnt;
    }
}