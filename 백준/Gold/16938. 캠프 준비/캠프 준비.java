import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int l;
    private static int r;
    private static int x;
    private static int[] num;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(answer);
    }

    private static void findAnswer(int idx, int count, int sum, int max, int min) {
        if (count >= 2) {
            if (l <= sum && sum <= r && max - min >= x) {
                answer++;
            }
        }

        for (int i = idx; i < n; i++) {
            findAnswer(i + 1, count + 1,sum + num[i] ,Math.max(max, num[i]), Math.min(min, num[i]));
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        l = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());

        num = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(num);
    }
}