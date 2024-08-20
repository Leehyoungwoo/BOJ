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
        long count = 0;  
        long[] result = new long[n];  
        for (int i = 1; i < n; i++) {
            double ratio = Math.ceil(Math.log(arr[i - 1] / (double)arr[i]) / Math.log(2)) + result[i - 1];
            if (ratio > 0) {
                result[i] = Math.max(0, (long)ratio); 
                count += result[i]; 
            }
        }
        System.out.println(count);
    }
}