import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int n, h;
    // 요거보다 커야 안걸림
    private static int[] bottom;
    // 요거보다 작아야 안걸림
    private static int[] top;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());
        bottom = new int[n / 2];
        top = new int[n / 2];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(input.readLine());
            if (i % 2 == 0) {
                bottom[i / 2] = num;
            } else {
                top[i / 2] = num;
            }
        }
    }

    private static void findAnswer() {
        Arrays.sort(bottom);
        Arrays.sort(top);

        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int height = 1; height <= h; height++) {
            int crashBottom = bottom.length - lowerBound(bottom, height);
            int crashTop = top.length - lowerBound(top, h - height + 1);

            int total = crashBottom + crashTop;

            if (total < min) {
                min = total;
                count = 1;
            } else if (total == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    private static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}