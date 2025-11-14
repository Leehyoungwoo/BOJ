import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] A, B, C, D;

    public static void main(String[] args) throws IOException {
        init();
        long count = findAnswer();

        System.out.println(count);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(input.readLine());
            A[i] = Integer.parseInt(token.nextToken());
            B[i] = Integer.parseInt(token.nextToken());
            C[i] = Integer.parseInt(token.nextToken());
            D[i] = Integer.parseInt(token.nextToken());
        }
    }

    private static long findAnswer() {
        // A와 B를 더한 값을 모두 구해 그럼 1600만일거 아냐?
        // 거기에 C + D 더한값을 모두 구해서 정렬해놔
        // 그리고 로어 바운드, 어퍼 바운드를 모두 구해서 어퍼 인덱스 - 로어 인덱스 하면 성립하는 개수가 되겠네
        int[] AB = new int[n * n];
        int[] CD = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        long count = 0;
        Arrays.sort(CD);
        for (int i = 0; i < AB.length; i++) {
            int sum = AB[i];
            int target = -sum;
            int lowerIdx = lowerBound(target, CD);
            int upperIdx = upperBound(target, CD);
            count += (long) upperIdx - lowerIdx;
        }

        return count;
    }

    private static int lowerBound(int sum, int[] arr) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < sum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int upperBound(int sum, int[] arr) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= sum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}