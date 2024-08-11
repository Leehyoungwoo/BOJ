import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] nums = new int[n];
        
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Arrays.sort(nums);
        
        int left = 0;
        int right = n - 1;
        int minAbsSum = Integer.MAX_VALUE;
        int recordL = 0;
        int recordR = 0;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            int absSum = Math.abs(sum);
            
            if (absSum < minAbsSum) {
                minAbsSum = absSum;
                recordL = nums[left];
                recordR = nums[right];
            }
            
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                break;
            }
        }
        
        if (recordL < recordR) {
            System.out.println(recordL + " " + recordR);
        } else {
            System.out.println(recordR + " " + recordL);
        }
    }
}