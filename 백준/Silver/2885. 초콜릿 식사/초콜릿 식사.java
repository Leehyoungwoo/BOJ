import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int k = Integer.parseInt(input.readLine());

        int chocho = 1;
        while (chocho < k) {
            chocho *= 2;
        }
        answer.append(chocho).append(" ");

        int remaining = chocho;
        int divide = 0;
        int mine = 0;

        if (chocho == k) {
            answer.append(0).append(" ");
            System.out.println(answer);
            return;
        }
        while (mine != k) {
            if (mine + remaining / 2 <= k) {
                mine += remaining / 2;
                remaining /= 2;
                divide++;
            } else {
                remaining /= 2;
                divide++;
            }
        }

        answer.append(divide);
        System.out.println(answer);
    }
}