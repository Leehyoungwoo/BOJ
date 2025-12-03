import java.io.*;
import java.util.*;

public class Main {

    private static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(input.readLine());
        int answer = 0;

        while (x > 0) {
            answer += x & 1;
            x >>= 1;
        }

        System.out.println(answer);
    }
}