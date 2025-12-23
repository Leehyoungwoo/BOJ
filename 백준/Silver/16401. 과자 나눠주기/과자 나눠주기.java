import java.io.*;
import java.util.*;

public class Main {

    private static int m, n;
    private static int[] cookie;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        cookie = new int[n];
        for (int i = 0; i < n; i++) {
            cookie[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int findAnswer() {
        // m은 조카, n은 과자의 수고
        // 과자를 하나로 합칠 수는 없음
        // 모든 조카한테 줘야함
        // 막대 과자의 최대 길이를 구하면?

        int answer = 0;
        int left = 1;
        int right = Arrays.stream(cookie)
                .max()
                .getAsInt();

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossibleToShare(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean isPossibleToShare(int target) {
        int count = 0;
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i] >= target) {
                count+= cookie[i] / target;
            }

            if (count >= m) {
                return true;
            }
        }

        return count >= m;
    }
}