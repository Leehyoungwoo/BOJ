import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int testcase;
    private static int n;
    private static int[] zero, one;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        testcase = Integer.parseInt(input.readLine());

        for (int i = 0; i < testcase; i++) {
            init(input);
            fibo(n);
            answer.append(zero[n]).append(" ").append(one[n]).append('\n');
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        zero = new int[Math.max(2, n + 1)]; // 최소 크기를 2로 설정
        one = new int[Math.max(2, n + 1)];
    }

    private static void fibo(int n) {
        zero[0] = 1;
        one[0] = 0;
        if (n >= 1) {
            zero[1] = 0;
            one[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            zero[i] = zero[i - 1] + zero[i - 2];
            one[i] = one[i - 1] + one[i - 2];
        }
    }
}