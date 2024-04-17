import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static int[] cost;
    private static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        arr = new int[n + 1];
        cost = new int[n + 2];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken() );
        }
        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            queue.add(new int[]{start, end, value});
        }
    }

    private static void findAnswer() {
        for (int i = 1; i <= m; i++) {
            int[] cur = queue.poll();
            int start = cur[0];
            int end = cur[1];
            int value = cur[2];
            cost[start] += value;
            cost[end + 1] -= value;
        }

        for (int i = 1; i <= n; i++) {
            cost[i] = cost[i - 1] + cost[i];
        }

        for (int i = 1; i <= n; i++) {
            arr[i] += cost[i];
        }
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(Arrays.toString(arr)
                .replaceAll("[\\[\\],]", "")
                .replaceFirst("0", "")
                .trim());
    }
}