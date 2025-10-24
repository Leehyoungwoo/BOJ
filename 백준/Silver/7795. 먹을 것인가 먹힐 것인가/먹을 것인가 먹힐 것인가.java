import java.io.*;
import java.util.*;

public class Main {

    private static int T;
    private static int n, m;
    private static int[] a;
    private static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(input.readLine());
        StringBuilder answer = new  StringBuilder();
        for (int i = 0; i < T; i++) {
            init(input);
            int result = findAnswer();
            answer.append(result).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        a = new int[n];
        b = new int[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int findAnswer() {
        Arrays.sort(b);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int target = a[i];
            // 자기보다 크기가 작은 먹이만 먹을 수 있대
            // 첫등장 => lowerBound
            int count = lowerBoundBinarySearch(target);
            sum += count;
        }

        return sum;
    }

    private static int lowerBoundBinarySearch(int target) {
        int left = 0;
        int right = m;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (b[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}