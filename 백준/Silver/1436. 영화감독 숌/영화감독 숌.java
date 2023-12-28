import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        int count = 0;
        int num = 666;

        while (count < N) {
            if (String.valueOf(num).contains("666")) {
                count++;
            }
            if (count == N) {
                break;
            }
            num++;
        }

        System.out.println(num);
    }
}