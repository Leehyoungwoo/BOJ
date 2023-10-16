import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static Queue<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        init();
        queueSetting();
        printAnswer();
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0;

        while (queue.size() > 1) {
            cnt++;
            if(cnt < K) {
                queue.add(queue.poll());
            } else {
                sb.append(queue.poll()).append(",").append(" ");
                cnt = 0;
            }
        }
        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }


    private static void queueSetting() {
        for (int i = 0; i < N; i++) {
            queue.add(i+1);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
    }
}