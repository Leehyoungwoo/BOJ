import java.io.*;
import java.util.*;

public class Main {
	
	private static String a;
	private static String b;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(findMin());
	}

	private static int findMin() {
		int n = reverseNum(a);
		int m = reverseNum(b);
		
		return Math.max(n, m);
	}

	private static int reverseNum(String str) {
		StringBuilder re = new StringBuilder();
		
		for (int i = str.length() - 1; i >= 0; i--) {
			re.append(str.charAt(i));
		}
		return Integer.parseInt(re.toString());
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] s = input.readLine().split(" ");
		a = s[0];
		b = s[1];
	}
}