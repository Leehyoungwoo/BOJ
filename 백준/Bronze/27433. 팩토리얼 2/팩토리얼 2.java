import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        long answer = 1;

        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                answer *= i;
            }
        }

            System.out.println(answer);
    }
}