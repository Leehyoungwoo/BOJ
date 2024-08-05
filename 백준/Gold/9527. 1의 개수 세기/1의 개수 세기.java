import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static long a;
    private static long b;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a = Long.parseLong(tokenizer.nextToken());
        b = Long.parseLong(tokenizer.nextToken());
    }

    private static void findAnswer() {
        long result = calculateCount(b) - calculateCount(a - 1);
        System.out.println(result);
    }

    private static long calculateCount(long number) {
        if (number == 0 || number == 1) {
            return number;
        }
        int digitCount = 0;
        long maxTwo = 1;
        while (maxTwo * 2 <= number) {
            maxTwo *= 2;
            digitCount++;
        }

        return digitCount * maxTwo / 2 + number - maxTwo + 1 + calculateCount(number - maxTwo);
    }
}