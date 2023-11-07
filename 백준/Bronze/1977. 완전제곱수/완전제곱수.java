import java.util.*;
import java.io.*;

public class Main {

	private static int n;
	private static int m;
	private static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		printAnswer();
	}

	private static void printAnswer() {
		int sum = 0;
		for (int i = n; i <= m; i++) {
			if(isMaginNum(i)) {
				sum+=i;
				if(list.isEmpty()) {
					list.add(i);
				}
			}
		}
		if(sum == 0) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(sum);
		System.out.println(list.get(0));
	}

	private static boolean isMaginNum(int num) {
		int n = (int)Math.sqrt(num);
		if(num == Math.pow(n, 2)) {
			return true;
		}
		return false;
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());
		m = Integer.parseInt(input.readLine());
	}

}