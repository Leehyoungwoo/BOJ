import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] zoo;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        zoo = new int[n + 1][3];
        zoo[0][0] = 1;
        zoo[0][1] = 0;
        zoo[0][2] = 0;
        for (int i = 1; i <= n; i++) {
            zoo[i][0] = (zoo[i - 1][0] + zoo[i - 1][1] + zoo[i - 1][2]) % 9901;
            zoo[i][1] = (zoo[i - 1][0] + zoo[i - 1][2]) % 9901;
            zoo[i][2] = (zoo[i - 1][0] + zoo[i - 1][1]) % 9901;
        }
        int result =  (zoo[n][0] + zoo[n][1] + zoo[n][2]) % 9901;
        System.out.println(result);
    }
}