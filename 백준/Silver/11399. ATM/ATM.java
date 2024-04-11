import java.io.*;
import java.util.*;

class Main {

    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }
        System.out.println(answer);
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
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }
    }
}