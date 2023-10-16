import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Task {
	int score;
	int time;
	
	public Task(int score, int time) {
		this.score = score;
		this.time = time;
	}
}

public class Main {
	
	private static int n;
	private static Stack<Task> stack = new Stack<>();
	private static int finalScore = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());
		
		for (int i = 0; i < n; i++) {
			String s = input.readLine();
			if(s.length() != 1) {
				String[] str = s.split(" ");
				stack.add(new Task(Integer.parseInt(str[1]), Integer.parseInt(str[2])));
			}
            if(stack.isEmpty()) {
				continue;
			}

			Task curTask = stack.pop();
			curTask.time--;
			if(curTask.time != 0) {
				stack.add(curTask);
				continue;
			}
			finalScore+=curTask.score;

		}
		
		System.out.println(finalScore);
	}
}