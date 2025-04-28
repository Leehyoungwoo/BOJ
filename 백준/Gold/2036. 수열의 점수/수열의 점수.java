import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }
    }

    private static void findAnswer() {
        PriorityQueue<Integer> minus = new PriorityQueue();
        PriorityQueue<Integer> plus = new PriorityQueue(Collections.reverseOrder());
        boolean isZeroExist = false;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > 0) {
                plus.offer(num);
            } else if (num == 0) {
                isZeroExist = true;
            } else {
                minus.offer(num);
            }
        }

        long sum = 0;
        while (!plus.isEmpty()) {
            if (plus.size() == 1) {
                sum += plus.poll();
                continue;
            }

            int large = plus.poll();
            int small = plus.poll();
            if (small == 1) {
                sum += small;
                sum += large;
            } else {
                sum += (long) small * large;
            }
        }

        while (!minus.isEmpty()) {
            if (minus.size() == 1) {
                if (isZeroExist) {
                    minus.poll();
                } else {
                    int num = minus.poll();
                    sum += num;
                }
                continue;
            }
            int small = minus.poll();
            int large = minus.poll();
            sum+=(long) small * large;
        }

        System.out.println(sum);
    }
}
