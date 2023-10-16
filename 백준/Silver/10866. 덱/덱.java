import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	private static Deque<Integer> deq = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String order = br.readLine();
			if (order.contains(" ")) {
				followOrder(order);
				continue;
			}
			sb.append(followOrder1(order)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int followOrder1(String order) {
		if (order.equals("front")) {
			if(deq.isEmpty()) {
				return -1;
			}
			return deq.peekFirst();
		}
		
		if (order.equals("back")) {
			if(deq.isEmpty()) {
				return -1;
			}
			return deq.peekLast();
		}
		
		if (order.equals("size")) {
			return deq.size();
		}
		
		if (order.equals("empty")) {
			if(deq.isEmpty()) {
				return 1;
			}
			return 0;
		}
		
		if (order.equals("pop_back")) {
			if(deq.isEmpty()) {
				return -1;
			}
			
			return deq.pollLast();
		}
		
		if (order.equals("pop_front")) {
			if(deq.isEmpty()) {
				return -1;
			}
			return deq.pollFirst();
		}
		
		return -2;
	}

	private static void followOrder(String order) {
			String[] s = order.split(" ");
			if (s[0].equals("push_back")) {
				deq.addLast(Integer.parseInt(s[1]));
				return;
			}
        
			deq.addFirst(Integer.parseInt(s[1]));
			return;
	}
}