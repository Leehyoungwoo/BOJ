import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] arr;
    private static int[] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        n = Integer.parseInt(input.readLine());
        arr = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        int m = Integer.parseInt(input.readLine());
        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            builder.append(prefix[end] - prefix[start - 1]).append("\n");
        }
        System.out.println(builder);
    }
}