import java.io.*;
import java.util.*;

public class Main {

    private static int n, d, k, c;
    private static int[] sushi;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(input.readLine());
        }
    }

    private static int findAnswer() {
        // 윈도우를 구성하고
        int count = 0;
        int[] counts = new int[d + 1];
        for (int i = 0; i < k; i++) {
            if (counts[sushi[i]] == 0) {
                count++;
            }
            counts[sushi[i]]++;
        }

        int max = 0;
        int left = 0;
        int right = k - 1;
        while (left < sushi.length) {
            counts[sushi[left]]--;
            if (counts[sushi[left]] == 0) {
                count--;
            }
            left++;

            right++;
            if (right == sushi.length) {
                right = 0;
            }

            counts[sushi[right]]++;
            if (counts[sushi[right]] == 1) {
                count++;
            }

            if (counts[c] == 0) {
                max = Math.max(max, count + 1);
            }  else {
                max = Math.max(max, count);
            }
        }

        return max;
    }
}