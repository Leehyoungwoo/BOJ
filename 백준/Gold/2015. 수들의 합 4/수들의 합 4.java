import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        num = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long findAnswer() {
        Map<Long, Long> freq = new HashMap<>();
        long sum = 0;
        long count = 0;
        freq.put(0L, 1L);

        for (int i : num) {
            sum += i;

            long need = sum - k;
            count += freq.getOrDefault(need, 0L);

            freq.put(sum, freq.getOrDefault(sum, 0L) + 1);
        }

        return count;
    }
}