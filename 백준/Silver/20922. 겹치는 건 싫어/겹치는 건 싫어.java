import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        arr = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int findAnswer() {
        int max = 0;
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (count.getOrDefault(arr[right], 0) < k) {
                count.put(arr[right], count.getOrDefault(arr[right], 0) + 1);
                right++;
            } else {
                count.put(arr[right], count.getOrDefault(arr[right], 0) + 1);
                while (count.getOrDefault(arr[right], 0) > k) {
                    count.put(arr[left],  count.getOrDefault(arr[left], 0) - 1);
                    left++;
                }
                right++;
            }
            max = Math.max(max, right - left);
        }

        return max;
    }
}
