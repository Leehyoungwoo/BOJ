import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int minDiff = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        while (right < n) {  // ✅ `right`가 `n-1`이 아닌 `n`까지 탐색 가능하도록 수정
            int diff = num[right] - num[left];

            if (diff >= m) { 
                minDiff = Math.min(minDiff, diff);
                left++;  
            } else {
                right++;  // ✅ `right`가 `n`이 되기 전에 조건을 체크할 수 있도록 유지
            }

            // ✅ `left`가 `right`보다 커지면 안 되므로 `right`를 따라가게 함
            if (left > right) {
                right = left;
            }
        }

        System.out.println(minDiff);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(num);
    }
}
