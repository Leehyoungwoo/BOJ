import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }
}