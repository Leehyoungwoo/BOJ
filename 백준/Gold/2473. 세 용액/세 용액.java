import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int n;
    private static int[] w;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        w = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(w);
        long min = Long.MAX_VALUE;
        int ans1= 0;
        int ans2 = 0;
        int ans3 = 0;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = (long) w[i] + w[left] + w[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    ans1 = w[i];
                    ans2 = w[left];
                    ans3 = w[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(ans1 + " " + ans2 + " " + ans3);
    }
}
