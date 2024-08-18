import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[5];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < 5; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum+=Math.pow(nums[i], 2);
        }
        System.out.println(sum % 10);
    }
}