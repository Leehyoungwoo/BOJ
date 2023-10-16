import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    private static int x;
    private static PriorityQueue<Integer> pque = new PriorityQueue<>(Comparator.reverseOrder());
    private static int[] order;

    public static void main(String[] args) throws IOException {
        init();
        followOrder();
    }

    private static void followOrder() {
        for (int i = 0; i < order.length; i++) {
            if(order[i] == 0) {
                if(pque.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(pque.poll());
                continue;
            }
            pque.add(order[i]);
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(input.readLine());
        order = new int[x];

        for (int i = 0; i < x; i++) {
            order[i] = Integer.parseInt(input.readLine());
        }
    }
}