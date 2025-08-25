import java.io.*;
import java.util.*;

public class Main {

    // 1~ n-1은 부품, n은 완제품 번호
    private static int n;
    private static int m;
    private static int made;
    private static List<List<int[]>> graph = new ArrayList<>();
    // 부품을 만드는데 필요한 관계 x, y, k는  X를 만드는데 y가 k개 필요하다

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        for (int i = 0; i <= n; i++) {
            List<int[]> list = new ArrayList<>();
            graph.add(list);
        }

        int low = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            String[] s = input.readLine().split(" ");
            int number = Integer.parseInt(s[0]);
            int puzzle = Integer.parseInt(s[1]);
            int count = Integer.parseInt(s[2]);
            low = Math.min(low, number);
            graph.get(number).add(new int[] {puzzle, count});
        }

        made = low - 1;
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        int[] need = new int[n + 1];
        make(n, 1, need);

        for (int i = 1; i < n; i++) {
            if (graph.get(i).isEmpty() && need[i] > 0) {
                answer.append(i).append(" ").append(need[i]).append("\n");
            }
        }

        System.out.println(answer);
    }

    private static void make(int product, int quantity, int[] need) {
        int partCount = graph.size() - 1;
        int[] required = new int[partCount + 1];  
        Queue<Integer> queue = new LinkedList<>();

        required[product] = quantity;
        queue.offer(product);

        while (!queue.isEmpty()) {
            int part = queue.poll();

            int amount = required[part];
            if (amount == 0) continue; 
            // 필요한 부품 수가 다른 재료와 섞이면 안되기 때문에 사용하면 0으로 초기화해줘야 함
            required[part] = 0;        

            // 기본 재료
            if (graph.get(part).isEmpty()) {
                need[part] += amount;  
                continue;
            }

            for (int[] sub : graph.get(part)) {
                int subPart = sub[0];
                int subCount = sub[1];
                required[subPart] += amount * subCount;
                queue.offer(subPart); 
            }
        }
    }
}