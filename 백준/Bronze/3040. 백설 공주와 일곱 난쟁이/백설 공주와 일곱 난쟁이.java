import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int[] dwarf = new int[9];
	private static final int dwarfSum = 100;
	private static int totalSum;

	public static void main(String[] args) throws IOException {
		init();
		StringBuilder sb = new StringBuilder();
		makeSum();
		int liarSum = totalSum - 100;
		findLiar(liarSum);
		
		for (int i = 0; i < dwarf.length; i++) {
			if(dwarf[i] == 0) {
				continue;
			}
			sb.append(dwarf[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void makeSum() {
		int sum = 0;
		for (int i = 0; i < dwarf.length; i++) {
			sum += dwarf[i];
		}
		
		totalSum = sum;
	}

	private static void findLiar(int liarSum) {
		for (int i = 0; i < dwarf.length - 1; i++) {
			for (int j = i + 1; j < dwarf.length; j++) {
				if(dwarf[i] + dwarf[j] == liarSum) {
					dwarf[i] = 0;
					dwarf[j] = 0;
					return;
				}
 			} 
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < dwarf.length; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
	}
}