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
        if (90 <= n && n <= 100) {
            System.out.println("A");
            return;
        }
        if (80 <= n && n <= 89) {
            System.out.println("B");
            return;
        }
        if (70 <= n && n <= 79) {
            System.out.println("C");
            return;
        }
        if (60 <= n && n <= 69) {
            System.out.println("D");
            return;
        }
        System.out.println("F");
    }
}