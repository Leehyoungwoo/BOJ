import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) {
        StringBuilder answer = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt(); // 이 부분을 수정했습니다.
            }
            int[] prefix = new int[n + 1];
            for (int j = 1; j < n + 1; j++) {
                prefix[j] = prefix[j - 1] + arr[j - 1];
            }
            int max = findAnswer(prefix);
            answer.append(max).append("\n");
        }
        System.out.println(answer);
    }

    private static int findAnswer(int[] prefix) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < prefix.length - 1; i++) {
            for (int j = i + 1; j < prefix.length; j++) {
                max = Math.max(max, prefix[j] - prefix[i]);
            }
        }
        return max;
    }
}