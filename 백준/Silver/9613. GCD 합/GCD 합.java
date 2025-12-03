import java.io.*;
import java.util.*;

public class Main {

    private static int t;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());

        for (int i = 0; i < t; i++) {
            init(input);
            long output = findAnswer();

            answer.append(output).append("\n");
        }

        System.out.println(answer);
    }

    private static long findAnswer() {
        long sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum += findGcd(nums[i], nums[j]);
            }
        }

        return sum;
    }

    private static void init(BufferedReader input) throws IOException {
        String[] line = input.readLine().split(" ");
        nums = new int[line.length - 1];
        for (int i = 0; i < Integer.parseInt(line[0]); i++) {
            nums[i] = Integer.parseInt(line[i + 1]);
        }
    }

    private static int findGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}