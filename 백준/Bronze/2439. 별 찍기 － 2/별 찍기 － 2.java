import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= n - 1 - i) {
                    answer.append("*");
                    continue;
                }
                answer.append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}