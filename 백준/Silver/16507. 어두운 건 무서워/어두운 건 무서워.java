import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int r;
    private static int c;
    private static int q;
    private static int[][] map;
    private static int[][] prefixSum;
    private static Queue<int[]> order = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        init(input);
        for (int i = 0; i < q; i++) {
            int[] cur = order.poll();
            int r1 = cur[0];
            int c1 = cur[1];
            int r2 = cur[2];
            int c2 = cur[3];
            int sum = prefixSum[r2][c2] - prefixSum[r2][c1 - 1] - prefixSum[r1 - 1][c2] + prefixSum[r1 - 1][c1 -1 ];
            int count = (r2 - r1 + 1) * (c2 - c1 + 1);
            builder.append(sum / count).append("\n");
        }

        System.out.println(builder);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        r = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        q = Integer.parseInt(tokenizer.nextToken());
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            order.offer(new int[] {a, b, c, d});
        }

        prefixSum = new int[r + 1][c + 1];
        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }
    }
}