import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        init();
        printMagicNumber(0, 0);
    }

    private static void printMagicNumber(int cnt,  int number) {
        if (cnt == N) {
            System.out.println(number);
            return;
        }
        
        for (int i = 1; i < 10; i++) {
            int temp = 10 * number + i;
            if (isPrime(temp)) {
                printMagicNumber(cnt+1, temp);
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
           return false;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }
}