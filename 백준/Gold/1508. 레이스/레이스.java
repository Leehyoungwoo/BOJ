import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int[] nums;
    private static int[] placement;
    private static int[] bestPlacement;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        nums = new int[k];
        placement = new int[k];
        bestPlacement = new int[k];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < k; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);
    }

    private static void findAnswer() {
        int left = 1;
        int right = nums[nums.length - 1] - nums[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlace(mid)) {
                System.arraycopy(placement, 0, bestPlacement, 0, k);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    private static boolean canPlace(int mid) {
        Arrays.fill(placement, 0);
        int count = 1;
        int lastPosition = nums[0];
        placement[0] = 1;
        for (int i = 1; i < k; i++) {
            if (nums[i] - lastPosition >= mid) {
                placement[i] = 1;
                lastPosition = nums[i];
                count++;
                if (count == m) {
                    for (int j = i + 1; j < k; j++) {
                        placement[j] = 0;
                    }
                    return true;
                }
            }
        }
        return count >= m;
    }

    private static void printAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < bestPlacement.length; i++) {
            answer.append(bestPlacement[i]);
        }
        System.out.println(answer);
    }
}