import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(answer());
    }

    private static int answer() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = que.poll();
        }
        return answer;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                que.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }
    }
}