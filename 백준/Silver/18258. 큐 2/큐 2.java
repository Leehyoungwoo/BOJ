import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String order;
    private static int N;
    private static Deque<Integer> que = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            order = br.readLine();
            if (order.contains("push")) {
                String[] s = order.split(" ");
                que.addLast(Integer.parseInt(s[1]));
            } else if (order.equals("front")) {
                if (que.size() == 0) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(que.peekFirst()).append("\n");
                }
            } else if (order.equals("empty")) {
                if(que.size() == 0) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (order.equals("back")) {
                if (que.size() == 0) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(que.peekLast()).append("\n");
                }
            } else if (order.equals("size")) {
                sb.append(que.size()).append("\n");
            } else if (order.equals("pop")) {
                if (que.size() == 0) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(que.pop()).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}