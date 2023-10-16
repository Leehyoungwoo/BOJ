import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int X = input.nextInt();
		int spin = 1;

		while (true) {

			if (X <= spin) {
				if (spin % 2 != 0) {
					System.out.println((spin + 1 - X) + "/" + X);
					break;
				}
				if (spin % 2 == 0) {
					System.out.println(X + "/" + (spin + 1 - X));
					break;
				}
			}
			if (X > spin) {
				X -= spin;
				spin ++;
			}
		}
	}
}