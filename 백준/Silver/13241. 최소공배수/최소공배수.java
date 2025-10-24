import java.io.*;
import java.util.*;

public class Main {
    
    private static long a, b;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a =  Long.parseLong(tokenizer.nextToken());
        b = Long.parseLong(tokenizer.nextToken());
    }

    private static void findAnswer() {
        long gcd = findGcd(a, b);
        System.out.println(a / gcd * b);
    }

    private static long findGcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}