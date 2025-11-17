import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] fruit;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        fruit = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            fruit[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int findAnswer() {
        int[] counts = new int[10];
        int kind = 0;
        int left = 0;
        int max = 0;
        for (int right = 0; right < n; right++) {
            int f = fruit[right];
            if (counts[f] == 0) {
                kind++;
            }

            counts[f]++;

            while (kind > 2) {
                int lf =  fruit[left];
                counts[lf]--;
                if (counts[lf] == 0) {
                    kind--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}