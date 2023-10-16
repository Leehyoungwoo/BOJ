import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int T;
	private static int N;
	private static int M;
	private static int[][] dp = new int[30][30];

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			init(input);
			int ans = findAnswer();
			answer.append(ans).append("\n");
		}

		System.out.println(answer);
	}

	private static void init(BufferedReader input) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
	}

	private static int findAnswer() {
		for (int i = 1; i < 30; i++) {
			for (int j = 1; j < 30; j++) {
				if(i == 1) {
					dp[i][j] = j;
					continue;
				}
				
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
			}
		}
		
		return dp[N][M];
	}

}