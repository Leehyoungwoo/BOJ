import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int testCase = input.nextInt(); 
		int data = 1; 
		int com[] = new int[testCase]; 

		for (int i = 0; i < testCase; i++) {
			int a = input.nextInt() % 10; 
			int b = input.nextInt() % 4;
			if (b == 0) {
				b = 4;
			}
			for (int j = 0; j < b; j++) {
				data = data * a;
			}

			if (data % 10 != 0) {
				com[i] = (data % 10);

			}
			if (data % 10 == 0) {
				com[i] = 10;
			}
			data = 1;//// 초기화
		}
		for (int k = 0; k < testCase; k++) {
			System.out.println(com[k]);
		}
	}
}