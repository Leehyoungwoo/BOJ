import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] num;
    private static int[] order;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        num = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
        m = Integer.parseInt(input.readLine());
        tokenizer = new StringTokenizer(input.readLine());
        order = new int[m];
        for (int i = 0; i < m; i++) {
            order[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        Arrays.sort(num);
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < m; i++) {
            int target = order[i];
            answer.append(binarySearch(target)).append("\n");
        }

        System.out.println(answer);
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                return 1;
            }
            if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }
}