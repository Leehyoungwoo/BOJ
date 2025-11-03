import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] num;
    private static int[] counter;
    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {
        init();
        String answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        num = new int[n];
        counter = new int[4];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++) {
            counter[i] = Integer.parseInt(tokenizer.nextToken());
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    private static String findAnswer() {
        findMinMax(1, new int[4], num[0]);
        return max + "\n" + min;
    }

    private static void findMinMax(int idx, int[] used, int total) {
        if (idx == n) {
            max = Math.max(max, total);
            min = Math.min(min, total);
            return;
        }
        // 덧셈, 뺄셈, 곱셉, 나눗셈 순
        for (int i = 0; i < 4; i++) {
            // 카운터가 없으면 지나치기
            if (used[i] == counter[i]) continue;

            used[i]++;
            if (i == 0) {
                findMinMax(idx + 1, used, total + num[idx]);
            }

            if (i == 1) {
                findMinMax(idx + 1, used, total - num[idx]);
            }

            if (i == 2) {
                findMinMax(idx + 1, used, total * num[idx]);
            }

            if (i == 3) {
                findMinMax(idx + 1, used, total / num[idx]);
            }
            // 백트래킹
            used[i]--;
        }
    }
}