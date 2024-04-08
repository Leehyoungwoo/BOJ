import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] arr;
    private static Queue<Integer> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = que.poll();
            int num = findAnswer(target);
            answer.append(num).append("\n");
        }
        System.out.println(answer);
    }

    private static int findAnswer(int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left<=right) {
            int mid = (left + right) / 2;
            if(arr[mid] < target) {
                left = mid + 1;
                continue;
            }

            if(arr[mid] > target) {
                right = mid - 1;
                continue;
            }
            return 1;
        }

        return 0;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        arr = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);
        m = Integer.parseInt(input.readLine());
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            que.offer(Integer.parseInt(tokenizer.nextToken()));
        }
    }
}