import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] lengths = new long[K];
        long maxLen = 0;

        for (int i = 0; i < K; i++) {
            lengths[i] = Long.parseLong(input.readLine());
            if (lengths[i] > maxLen) {
                maxLen = lengths[i];
            }
        }

        long left = 1;
        long right = maxLen;
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (long length : lengths) {
                count += (length / mid);
            }

            if (count >= N) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}