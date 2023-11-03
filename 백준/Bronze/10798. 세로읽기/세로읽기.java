import java.util.*;
import java.io.*;

public class Main {
	
	private static char[][] arr = new char[5][15];
	private static StringBuilder answer = new StringBuilder();
			
	public static void main(String[] args) throws IOException {
		init();
		wireOrChar();
		System.out.println(answer);
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < arr.length; i++) {
			String str = input.readLine();
			for (int j = 0; j < str.length(); j++) {
				arr[i][j] = str.charAt(j);
			}
		}
	}
	
	private static void wireOrChar() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 5; j++) {
				if(arr[j][i] == '\0' ) {
					continue;
				}
				answer.append(arr[j][i]);
			}
		}
	}
}