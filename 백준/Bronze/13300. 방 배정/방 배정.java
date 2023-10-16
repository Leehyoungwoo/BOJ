import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int K = input.nextInt();
		int[][] arr = new int[6][2];
		int cnt = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = 0; /////////// 0값을 넣어줘야 1플러스 가능
			}
		}

		for (int i = 0; i < N; i++) {
			int S = input.nextInt(); ///// 성별
			int Y = input.nextInt(); /////// 학년
			arr[Y - 1][S] += 1; ///////// 배열에 입력한 학생정보 정리
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] % K == 0) {
					cnt += (arr[i][j] / K);
				}
				if (arr[i][j] % K != 0) {
					cnt += (arr[i][j] / K) + 1;
				}
			}
		}
		System.out.println(cnt);
	}
}