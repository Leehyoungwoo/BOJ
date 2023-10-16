import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int[] student = new int[N];
		int[] arr = new int[N]; ////// 줄 선 순서
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < student.length; i++) {
			student[i] = input.nextInt(); ///// 각각 뽑은 번호 입력
		}
		for (int j = 0; j < arr.length; j++) {
			if (student[j] == 0) {
				arr[j] = (j + 1);
			}
			if (student[j] != 0) {
				arr[j] = (j + 1);
				for (int k = j; k > j - student[j]; k--) {
					int tmp = 0;
					tmp = arr[k - 1];
					arr[k - 1] = arr[k];
					arr[k] = tmp;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}
}
