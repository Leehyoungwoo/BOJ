import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        long k = Long.parseLong(tokenizer.nextToken());

        long[] distances = new long[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            long d = Long.parseLong(tokenizer.nextToken());
            distances[i] = distances[i - 1] + d;
        }

        long totalDistance = distances[n];

        if (k <= totalDistance) {
            int result = lowerBound(distances, k);
            System.out.println(result);
        } else {
            long remainingDistance = 2 * totalDistance - k;
            int result = lowerBound(distances, remainingDistance);
            System.out.println(result);
        }
    }

    private static int lowerBound(long[] prefixSum, long target) {
        int left = 1;
        int right = prefixSum.length - 1;
        int answer = 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (prefixSum[mid] > target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
