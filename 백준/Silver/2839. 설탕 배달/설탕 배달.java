import java.util.*;

public class Main {
	
	private static int N;
	private static final int LARGE_BAG = 5;
	private static final int SMALL_BAG = 3;
	
	public static void main(String[] args) {
		init();
		findTotalBagCount();
		System.out.println(findTotalBagCount());
	}


	private static int findTotalBagCount() {
		int large = 0;
		int small = 0;
		
		while (small <= (N / SMALL_BAG)) {
			large = (N - small * SMALL_BAG) / LARGE_BAG;
			
			if ((N - small * SMALL_BAG) % LARGE_BAG == 0) {
				return large + small;
			}
			small++;
		}
		if (N % SMALL_BAG != 0) {
			return -1;
		}
		return large + small;
	}


	private static void init() {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
	}
}		