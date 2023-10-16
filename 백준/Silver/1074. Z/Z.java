import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int r;
	private static int c;
	private static int size;
	private static int count = 0;
	
	public static void main(String[] args) throws IOException {
		init();
		findAnswer();
		System.out.println(count);
	}

	private static void findAnswer() {
		while(size != 1) {
			if(r < size / 2 && c < size / 2) {
				size /= 2;
				continue;
			}
			
			if(r < size/2 && c >= size / 2) {
				count += (size * size) / 4;
				c-=size/2;
				size/=2;
				continue;

			}
			
			if(r >= size/2 && c < size/2) {
				count += (size * size) / 4 * 2;
				r-= size/2;
				size/=2;
				continue;
			}
			
			else {
				count += (size * size) / 4 * 3;
				r-=size/2;
				c-=size/2;
				size/=2;
			}
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);	
	}
}