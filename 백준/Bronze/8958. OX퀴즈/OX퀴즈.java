import java.util.Scanner;

public class Main {

	int scoreCnt;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int testCase = input.nextInt();

		for (int i = 0; i < testCase; i++) {
			String put = input.next();
			cacululateScore(put);
		}
	}

	public static void cacululateScore(String put) {
		
		int scoreCnt = 0;
		int score = 0;
		for (int i = 0; i < put.length(); i++) {
			if (put.charAt(i) == 'O') {
				scoreCnt++;
				score += scoreCnt;
			} else {
				scoreCnt = 0;
			}
		}
		System.out.println(score);
	}
}