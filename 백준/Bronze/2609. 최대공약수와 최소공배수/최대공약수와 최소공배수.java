import java.io.*;
import java.util.*;

public class Main {

    private static int a, b;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int gcd = findGCD(a, b);
        System.out.println(gcd + "\n" + a / gcd * b);
    }

    private static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
    }
}