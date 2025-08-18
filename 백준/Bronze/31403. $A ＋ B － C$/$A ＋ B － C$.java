import java.io.*;
import java.util.*;

public class Main {

    private static String a;
    private static String b;
    private static String c;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        a = input.readLine();
        b = input.readLine();
        c = input.readLine();
        System.out.println(Integer.parseInt(a) + Integer.parseInt(b) - Integer.parseInt(c));
        System.out.println(Integer.parseInt(a + b) - Integer.parseInt(c));
    }
}
