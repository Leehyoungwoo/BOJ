import java.io.*;
import java.util.*;

public class Main {

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder answer = new StringBuilder();

        while (!(line = input.readLine()).equals("0 0 0")) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            nums = new int[3];
            nums[0] = Integer.parseInt(tokenizer.nextToken());
            nums[1] = Integer.parseInt(tokenizer.nextToken());
            nums[2] = Integer.parseInt(tokenizer.nextToken());
            Arrays.sort(nums);
            if (isCorrect(nums)) {
                answer.append("right").append("\n");
            } else {
                answer.append("wrong").append("\n");
            }
        }

        System.out.println(answer);
    }

    private static boolean isCorrect(int[] nums) {
        return (int) Math.pow(nums[0], 2) + (int) Math.pow(nums[1], 2) == (int) Math.pow(nums[2], 2);
    }
}