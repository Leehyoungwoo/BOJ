import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static boolean[] num;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        num = new boolean[2000001];
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(input.readLine());
            num[number + 1000000] = true;
        }
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            if (num[i]) {
                answer.append(i - 1000000).append("\n");
            }
        }

        System.out.print(answer);
    }
}