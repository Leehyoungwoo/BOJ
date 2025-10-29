import java.io.*;
import java.util.*;

public class Main {

    private static int a, k;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
    }

    private static int findAnswer() {
        int count =  0;
        while (k != a) {
            count++;
            if (k % 2 == 0 && k / 2 >= a) {
                k = k / 2;
            } else {
                k = k  - 1;
            }
            if (a == k) {
                break;
            }
        }

        return count;
    }
}