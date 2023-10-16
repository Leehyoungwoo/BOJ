import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int A = input.nextInt();
        int B = input.nextInt();
        int C = input.nextInt();
        int sale = 0;

        while (!((C - B) * sale > A)) {
            if (B >= C) {
                sale = -1;
                break;
            }
            sale++;
        }
        System.out.println(sale);
    }
}