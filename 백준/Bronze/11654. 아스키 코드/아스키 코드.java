import java.io.*;
import java.util.*;

public class Main {

    private static String line;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        line = input.readLine();
        System.out.println(line.charAt(0) + '0' - '0');
    }
}
