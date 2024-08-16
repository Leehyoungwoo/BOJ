import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[] jw;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        jw = new int[m];
        for (int i = 0; i < m; i++) {
            jw[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(jw);
        int answer = 0;
        int left = 0;
        int right = jw[m - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canDistribute(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean canDistribute(int min) {
        if (min == 0) {
            return false;
        }
        int count = 0;

        for (int jewels : jw) {
            count += (jewels + min - 1) / min;
            if (count > n) {
                return false; 
            }
        }

        return count <= n;
    }
}