import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int L;
	private static int[] fruits;

	public static void main(String[] args) throws IOException {
		init();
		Arrays.sort(fruits);
		growingSnakeBird();
		System.out.print(L);
	}

	private static void growingSnakeBird() {
		for (int i = 0; i < fruits.length; i++) {
			int fruit = fruits[i];
			if(fruit <= L) {
				L++;				
			}
		}	
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		fruits = new int[N];
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < fruits.length; i++) {
			fruits[i] = Integer.parseInt(st1.nextToken());
		}
	}
}