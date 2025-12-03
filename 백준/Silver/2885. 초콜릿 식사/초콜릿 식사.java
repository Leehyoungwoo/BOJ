import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(input.readLine());

        int size = 1;
        while (size < K) {
            size <<= 1;
        }

        int cut = 0;
        int cur = size;
        int sum = 0;

        while (sum != K) {
            if (sum + cur <= K) {
                sum += cur;
            } else {
                cur >>= 1;
                cut++;
            }
        }

        System.out.println(size + " " + cut);
    }
}