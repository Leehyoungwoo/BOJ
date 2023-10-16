import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int L;
	private static int C;
	private static char[] ch;
	private static ArrayList<Character> consonants = new ArrayList<>();
	private static char[] temp;
	private static int[] idxarr;

	public static void main(String[] args) throws IOException {
		init();
		findPassword(0, 0);
	}

	private static void findPassword(int idx, int start) {
		if (idx == L) {
			temp = makingTemp();
			if(!isValidArr(temp)) {
				return;
			}
			printTemp(temp);
			System.out.println();
			return;
		}
		
		if (start == C) {
			return;
		}
		
		idxarr[idx] = start;
		findPassword(idx + 1, start + 1);
		idxarr[idx] = -1;
		findPassword(idx, start + 1);
	}

	private static boolean isValidArr(char[] temp2) {
		int consonantsCnt = 0;
		int vowelsCnt = 0;
		
		for (int i = 0; i < temp.length; i++) {
			if(consonants.contains(temp[i])) {
				consonantsCnt++;
				continue;
			}
			vowelsCnt++;
		}
		
		if(vowelsCnt >=2 && consonantsCnt >= 1) {
			return true;
		}
		
		return false;
	}

	private static void printTemp(char[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.print(temp[i]);
		}
	}

	private static char[] makingTemp() {
		char[] temp = new char[L];
		
		for (int i = 0; i < idxarr.length; i++) {
			temp[i] = ch[idxarr[i]];
		}
		
		return temp;
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		L = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		temp = new char[L];
		ch = new char[C];
		idxarr = new int[L];
		StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
		
		for (int i = 0; i < C; i++) {
			ch[i] = tokenizer2.nextToken().charAt(0);
		}
		
		Arrays.sort(ch);
		consonants.add('a');
		consonants.add('e');
		consonants.add('i');
		consonants.add('o');
		consonants.add('u');
	}
}