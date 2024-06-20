import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            pq.offer(input.nextInt());
        }
        
        int sum = 0;
        
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int mergeCost = first + second;
            sum += mergeCost;
            pq.offer(mergeCost);
        }
        
        System.out.println(sum);
    }
}
