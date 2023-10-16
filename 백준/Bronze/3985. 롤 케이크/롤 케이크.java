import java.util.*;

public class Main {
	
	private static int L;
	private static int N;
	private static int[] cake;
	private static int[] expectant;
	private static int[] wanted;
	
	public static void main(String[] args) {
		init();
		findExpection();
		System.out.println(answer1());
		System.out.println(answer2());
	}
		
	private static int answer1() {
		int maxWantMan = 1;
		
		for (int i = 1; i < wanted.length; i++) {
			if(wanted[i] > wanted[maxWantMan]) {
				maxWantMan = i;
			}
		}
		return maxWantMan;
	}

	private static int answer2() {
		int maxMan = 1;
		
		for (int i = 1; i < expectant.length; i++) {
			if(expectant[i] > expectant[maxMan]) {
				maxMan = i;
			}
		}
		return maxMan;
	}

		private static void init() {
			Scanner input = new Scanner(System.in);
			L = input.nextInt();
			N = input.nextInt();
			cake = new int[L + 1];
			expectant = new int[N+1];
			wanted = new int[N + 1];
			
			for (int i = 1; i <= N; i++) {
				int p = input.nextInt();
				int k = input.nextInt();
				wanted[i] = k - p + 1;
				
				for (int j = p; j <= k; j++) {
					if(cake[j] != 0) {
						continue;
					}
					cake[j] = i;
				}	
			}
		}
		
		private static void findExpection() {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j < cake.length; j++) {
					if(cake[j] == i) {
						expectant[i]++;
					}
				}
			}
		}
}