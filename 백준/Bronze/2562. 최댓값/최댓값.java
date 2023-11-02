import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static int[] arr = new int[9];
	private static int[] copyArr = new int[9];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = input.nextInt();
			copyArr[i] = arr[i];
		}
		
		Arrays.sort(copyArr);
		for (int i = 0; i < copyArr.length; i++) {
			if(arr[i] == copyArr[8]) {
				System.out.println(copyArr[8]);
				System.out.println(i + 1);
				break;
			}
		}
	}

}