import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        init();
        int[] answer = findAnswer();

        System.out.println(num[answer[0]] + " " + num[answer[1]]);
    }

    private static int[] findAnswer() {
        Arrays.sort(num);
        // 투포인터로 풀어보자
        int left = 0;
        int right = n -1;
        int[] best = new int[2];
        int bestMin = Integer.MAX_VALUE;

        while (left < right) {
            int sum = num[left] + num[right];
            int abs = Math.abs(sum);

            if (abs < bestMin) {
                bestMin = abs;
                best[0] = left;
                best[1] = right;
            }

            if (abs == 0) {
                break;
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        return best;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        num = new int[n];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}