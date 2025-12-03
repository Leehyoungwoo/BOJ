import java.io.*;
import java.util.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());

//        System.out.println(Integer.bitCount(n) == 1 ? 1 : 0);
        System.out.println((n & (n - 1)) == 0 ? 1 : 0);
    }
}