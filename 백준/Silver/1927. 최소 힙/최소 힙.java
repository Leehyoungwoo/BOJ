import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(input.readLine());
            if (num == 0) {
                if (pq.isEmpty()) {
                    answer.append("0").append("\n");
                } else {
                    answer.append(pq.poll()).append("\n");
                }
            } else {
                pq.add(num);
            }
        }

        System.out.println(answer);
    }
}