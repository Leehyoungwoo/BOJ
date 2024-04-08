import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int q;
    private static Queue<int[]> order = new LinkedList<>();
    private static int[] a;
    private static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        init(input);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int[] cur = order.poll();
            builder.append(b[cur[1]] - b[cur[0] - 1]).append("\n");
        }

        System.out.println(builder);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        q = Integer.parseInt(tokenizer.nextToken());
        a = new int[n + 1];
        b = new int[n + 1];

        // 배열 입력받기
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i < a.length; i++) {
            a[i] = Integer.parseInt(tokenizer.nextToken());
        }
        // 명령어 입력받기
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int[] arr = new int[]{start, end};
            order.offer(arr);
        }
        // 비내림차순
        Arrays.sort(a);
        for (int i = 1; i < b.length; i++) {
            b[i] = b[i - 1] + a[i];
        }
    }
}