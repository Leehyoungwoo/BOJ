import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(input.readLine());

        int[] A = new int[n];
        int[] B = new int[n];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = A[i];
            arr[i][1] = B[i];
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        long answer = 0;
        long previous = arr[0][1];
        long curMax = -1;

        for (int i = 0; i < n; i++) {
            if (previous > arr[i][0]) {
                long diff = previous - arr[i][0];
                long cnt = (diff + 29) / 30;
                answer += cnt;
                arr[i][0] += cnt * 30;
            }
            curMax = Math.max(curMax, arr[i][0]);

            if (i + 1 < n && arr[i][1] != arr[i + 1][1]) {
                previous = curMax;
            }
        }

        System.out.println(answer);
    }
}