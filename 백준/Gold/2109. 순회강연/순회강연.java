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
            String[] s = input.readLine().split(" ");
            classes.add(new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])});
        }
    }

    private static void findAnswer() {
        if (n == 0) {
            System.out.println(0);
            return;
        }
        // 마감일자 d, 페이 p가 있는데
        // 하루에 한곳만 강의 가능
        // 우선순위 큐는 돈을 많이 벌어야하니까 무조건 페이가 높은 순서
        // 리스트는 마감일이 역순 처리해서 PQ에 넣어버리고 하루에 하나씩 페이가 높은 순으로 처리
        classes.sort((a, b) -> b[1] - a[1]);
        int totalPay = 0;
        PriorityQueue<Integer> pay = new PriorityQueue<>(Collections.reverseOrder());
        int lastDay = classes.get(0)[1];
        int idx = 0;
        for (int day = lastDay; day > 0; day--) {
            while (idx < n && classes.get(idx)[1] == day) {
                pay.add(classes.get(idx)[0]);
                idx++;
            }

            if (!pay.isEmpty()) {
                totalPay += pay.poll();
            }
        }

        System.out.println(totalPay);
    }
}