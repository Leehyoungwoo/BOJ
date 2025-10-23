import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] card;
    private static int[] signal;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        card = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(tokenizer.nextToken());
        }
        m = Integer.parseInt(input.readLine());
        signal = new int[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            signal[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        Arrays.sort(card);
        for (int i = 0; i < m; i++) {
            int target = signal[i];
            int exist = binarySearch(target);
            if (exist == -1) {
                answer.append(0).append(" ");
            } else {
                int low = binarySearchLowerBound(target);
                int up = binarySearchUpperBound(target);
                answer.append(up - low).append(" ");
            }
        }

        System.out.println(answer);
    }

    private static int binarySearchUpperBound(int target) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid =  left + (right - left) / 2;
            if (card[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (card[mid] == target) {
                return mid;
            }

            if (card[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int binarySearchLowerBound(int target) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (card[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}