import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int x;
    private static int y;
    private static int w;
    private static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] str = input.readLine().split(" ");
        x = Integer.parseInt(str[0]);
        y = Integer.parseInt(str[1]);
        w = Integer.parseInt(str[2]);
        s = Integer.parseInt(str[3]);

        long time1 = (long)(x + y) * w;

        long time2;
        if ((x + y) % 2 == 0) {
            time2 = (long)Math.max(x, y) * s;
        } else {
            time2 = (long)(Math.max(x, y) - 1) * s + w;
        }
        long time3 = (long)Math.min(x, y) * s + (long)Math.abs(x - y) * w;
        long result = Math.min(time1, Math.min(time2, time3));

        System.out.println(result);
    }
}