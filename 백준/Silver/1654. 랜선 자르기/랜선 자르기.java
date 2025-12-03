import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int k, n;
    private static long[] len;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static long findAnswer() {
        // 자체적으로 K개의 랜선 가지고 있는데 N개의 같은 길이의 랜선으로 만들고 싶음
        // 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다
        long left = 1;
        long right = Arrays.stream(len)
                .max()
                .getAsLong();
        // 만들 수 있는 최대 랜선의 길이를 구해야하면
        long answer = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isPossibleToCut(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean isPossibleToCut(long mid) {
        long sum = 0;
        for (int i = 0; i < len.length; i++) {
            sum += (len[i] / mid);
            if (sum >= n) {
                return true;
            }
        }

        return sum >= n;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        k = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        len = new long[k];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            len[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}