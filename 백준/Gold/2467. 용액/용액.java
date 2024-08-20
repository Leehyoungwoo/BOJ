import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int[] solutions = new int[n];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int left = 0;
        int right = n - 1;
        int bestLeft = left;
        int bestRight = right;
        int minAbsSum = Integer.MAX_VALUE;

        while (left < right) {
            int sum = solutions[left] + solutions[right];
            if (Math.abs(sum) < minAbsSum) {
                minAbsSum = Math.abs(sum);
                bestLeft = left;
                bestRight = right;
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(solutions[bestLeft] + " " + solutions[bestRight]);
    }
}