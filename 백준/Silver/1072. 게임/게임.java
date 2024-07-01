import java.util.*;
import java.io.*;

public class Main {

    private static long X;
    private static long Y;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        X = Long.parseLong(s[0]);
        Y = Long.parseLong(s[1]);
        int Z = (int) ((100 * Y) / X);
        if (Z >= 99) {
            System.out.println(-1);
            return;
        }
        long left = 0;
        long right = 1000000000L;

        while (left < right) {
            long mid = (left + right) / 2;
            if (100 * (Y + mid) / (X + mid) > Z) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}