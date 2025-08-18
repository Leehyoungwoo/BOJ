import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            max = Math.max(max, number);
            min = Math.min(min, number);
        }

        System.out.println(min + " " + max);
    }
}