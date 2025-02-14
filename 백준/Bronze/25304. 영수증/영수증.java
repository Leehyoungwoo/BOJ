import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(input.readLine());
        int n = Integer.parseInt(input.readLine());
        int sum = 0;
        for (int i = 0; i < n; i++) {
            String[] in = input.readLine().split(" ");
            int price = Integer.parseInt(in[0]);
            int quantity = Integer.parseInt(in[1]);
            sum += price * quantity;
        }
        if (sum == x) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}