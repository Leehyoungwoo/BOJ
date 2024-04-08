import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int q;
    private static int[] arr;
    private static int[] prefixSum;
    private static Queue<int[]> order = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        init(input);
        for (int i = 0; i < q; i++) {
            int[] cur = order.poll();
            int curSum = prefixSum[cur[1]] - prefixSum[cur[0]];
            answer.append(curSum).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        q = Integer.parseInt(input.readLine());
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            order.offer(new int[]{start, end});
        }

        prefixSum = new int[n + 1];
        for (int i = 2; i < prefixSum.length; i++) {
            if (arr[i - 1] < arr[i - 2]) {
               prefixSum[i] = prefixSum[i - 1] + 1;
               continue;
            }
            prefixSum[i] = prefixSum[i - 1];
        }
    }
}