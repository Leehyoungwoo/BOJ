import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static int a;
    static int[] b = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(input.readLine());
        String s = input.readLine();

        for (int i = 0; i < 3; i++) {
            b[i] = s.charAt(i) - '0';
        }

        for (int i = 2; i >=  0; i--) {
            System.out.println(a * b[i]);
        }

        System.out.println(a * Integer.parseInt(s));
    }

}