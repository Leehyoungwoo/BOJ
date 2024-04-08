import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static long[] arr;
    private static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        init(input);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        arr = new long[n];
        prefixSum = new long[n + 1];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(tokenizer.nextToken());
        }

        long cnt = 0;
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }

        for (int i = 1; i < prefixSum.length; i++) {
            cnt += map.getOrDefault(prefixSum[i] - k, 0L);
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0L) + 1);
        }

        System.out.println(cnt);
    }
}