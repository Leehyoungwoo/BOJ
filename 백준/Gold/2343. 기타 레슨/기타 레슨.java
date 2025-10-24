import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] time;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        time = new int[n];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        // time에 있는 강의를 묶어야하는데
        // 블루레이 개수는 m개보다 작거나 같아야 함, 크기는 묶인 강의의 합
        // 찾는 값은 블루레이의 크기가 처음 나오는 구간
        //
        int left = 0;
        int right = Arrays.stream(time).sum();
        for (int t : time) {
            left = Math.max(left, t);
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 지금 가능하면 더 줄여보자
            // 불가능하면 늘리자
            if (isPossible(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    private static boolean isPossible(int target) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (sum + time[i] <= target) {
                sum += time[i];
            } else {
                sum = time[i];
                count++;
            }
        }

        return count + 1 <= m;
    }
}