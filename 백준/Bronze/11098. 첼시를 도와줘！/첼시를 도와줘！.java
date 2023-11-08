import java.util.*;
import java.io.*;

class Player implements Comparable<Player>{
	int price;
	String name;

	public Player(int price, String name) {
		super();
		this.price = price;
		this.name = name;
	}

	@Override
	public int compareTo(Player target) {
		return target.price - this.price;
	}

}

public class Main {
	
	private static int T;
	private static int n;
	private static List<Player> players;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(input.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			init(input);
			String maxMan = findMaxPricePlayer();
			answer.append(maxMan).append("\n");
		}
		
		System.out.println(answer);
	}

	private static String findMaxPricePlayer() {
		return players.get(0).name;
	}

	private static void init(BufferedReader input) throws IOException {
		players = new ArrayList<>();
		n = Integer.parseInt(input.readLine());
		
		for (int i = 0; i < n; i++) {
			String[] s = input.readLine().split(" ");
			int price = Integer.parseInt(s[0]);
			String name = s[1];
			players.add(new Player(price, name));
		}
		
		Collections.sort(players);
	}
}