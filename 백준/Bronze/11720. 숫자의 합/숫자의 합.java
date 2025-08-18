import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        String line = input.readLine();
        num = new int[n];
        for (int i = 0; i < line.length(); i++) {
            num[i] = line.charAt(i) - '0';
        }
    }

    private static void findAnswer() {
        int answer = Arrays.stream(num).sum();
        System.out.println(answer);
    }
}
