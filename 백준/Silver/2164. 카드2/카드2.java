import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static Queue<Integer> queue = new LinkedList<>();

    private static int N;

    public static void main(String[] args) throws IOException {
        init();
        cardSetting();
        findAnswer();
        System.out.println(queue.peek());
    }

    private static void findAnswer() {

        while (queue.size() > 1) {
            queue.poll();
            queue.add(queue.poll());
        }
    }

    private static void cardSetting() {
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }
}