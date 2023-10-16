import java.util.Scanner;

public class Main {
	static int[] status;
	static int sex;
	static int num;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int swich = input.nextInt();
		status = new int[swich];

		for (int i = 0; i < swich; i++) {
			status[i] = input.nextInt();
		}
		int student = input.nextInt();

		for (int i = 0; i < student; i++) {
			sex = input.nextInt();
			num = input.nextInt();
			ifBoy();
			ifGirl();
		}

		int br = 0;
		for (int i = 0; i < status.length; i++) {
			System.out.print(status[i] + " ");
			br++;

			if (br == 20) {
				System.out.println();
				br = 0;
			}
		}
	}

	public static void ifBoy() {
		if (sex == 1) {
			for (int i = 0; i < status.length / num; i++) {
				if (status[num * (i + 1) - 1] == 1) {
					status[num * (i + 1) - 1] = 0;
					continue;
				}

				if (status[num * (i + 1) - 1] == 0) {
					status[num * (i + 1) - 1] = 1;
				}
			}
		}
	}

	public static void ifGirl() {
		if (sex == 2) {
			int left = num - 2;
			int right = num;

			while (left >= 0 && right < status.length && status[left] == status[right]) {
				left--;
				right++;
			}

			for (int i = left + 1; i < right; i++) {
				status[i] = (status[i] + 1) % 2;
			}
		}
	}

}