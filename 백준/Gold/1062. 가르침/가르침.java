import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    private static int n, k;
    private static String[] word;
    private static int learned;
    private static int max;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        word = new String[n];
        for (int i = 0; i < n; i++) {
            word[i] = input.readLine();
        }
        String base = "antic";
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            learned |= (1 << (c - 'a'));
        }
    }

    private static void findAnswer() {
        if (k < 5) {
            System.out.println(0);
            return;
        }

        if (k == 26) {
            System.out.println(n);
            return;
        }

        max = Integer.MIN_VALUE;
        findMax(0, learned, k - 5);

        System.out.println(max);
    }

    private static void findMax(int idx, int learned, int left) {
        if (left == 0) {
            int count = countReadable(learned);
            max = Math.max(max, count);
            return;
        }

        if (idx == 26) {
            return;
        }

        if ((learned & (1 << idx)) != 0) {
            findMax(idx + 1, learned, left);
            return;
        }

        findMax(idx + 1, learned | (1 << idx), left - 1);

        findMax(idx + 1, learned, left);
    }

    private static int countReadable(int learned) {
        int count =0;
        for (String w : word) {
            boolean flag = false;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if ((learned & (1 << (c - 'a'))) == 0) {
                    flag = true;
                }
            }
            if (!flag) {
                count++;
            }
        }

        return count;
    }
}