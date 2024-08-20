import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] arr = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                while (arr[i] < arr[i - 1]) {
                    arr[i] *= 2;
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}