import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(expected);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int realRanked = i + 1;
            sum += Math.abs(expected[i] - realRanked);
        }
        System.out.println(sum);
    }
}