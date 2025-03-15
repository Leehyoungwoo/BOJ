import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<int[]> classes;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        classes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = input.readLine().split(" ");
            classes.add(new int[]{Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])});
        }
    }

    private static void findAnswer() {
        classes.sort(Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a-> a[1]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] time : classes) {
            // 만약에 수업을 할 때, 가장 먼저 끝나는 수업의 종료 시작이 수업 시작 시간과 같으면 해당 끝나는 강의실에 수업 가능 => 우선순위 큐에서 뺌
            if (!endTimes.isEmpty() && endTimes.peek() <= time[0]) {
                endTimes.poll();
            }
            // 큐에 수업을 넣어주고 
            endTimes.offer(time[1]);
        }
        
        // 총 남아있는 수업의 수가 필요한 강의실의 수
        System.out.println(endTimes.size());
    }
}