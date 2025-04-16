import java.io.*;
import java.util.*;

public class Main {

    private static int n, c;
    private static int m;
    private static List<int[]> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(input.readLine());

        inputs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int box = Integer.parseInt(tokenizer.nextToken());
            inputs.add(new int[]{start, end, box});
        }
    }

    private static void findAnswer() {
        int truck = 0;
        int totalSum = 0;
        inputs.sort(Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));
        // 마을 출발, 마을 뒤쪽으로 정렬해놓고 que에 집어넣고 빼면서 가보자
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < inputs.size(); i++) {
            que.offer(inputs.get(i));
        }
//        for (int i = 0; i < inputs.size(); i++) {
//            int[] cur = inputs.get(i);
//            System.out.println(cur[0] + " " + cur[1] + " " + cur[2]);
//        }
        int[][] fromTo = new int[n + 1][n + 1];
        for (int vilige = 1; vilige <= n; vilige++) {
            // 도착하면 택배 배달부터 하고
//            System.out.println(vilige + "번째 마을 시작");

            for (int i = 1; i <= n; i++) {
//                System.out.println("내릴 택배: " + fromTo[i][vilige]);
                truck-=fromTo[i][vilige];
            }
            // 마을 도착하고 실을 박스
            while(!que.isEmpty() && que.peek()[0] == vilige && truck <= c) {
                int[] boxes = que.poll();
                int start = boxes[0];
                int end = boxes[1];
                int box = boxes[2];
                // 트럭에 담을 수 있는 박수 수 정하기 + fromto에 기록
                if (box + truck < c) {
                    fromTo[start][end] = box;
                    truck+=box;
                    totalSum += box;
//                    System.out.println("실을 택배: " + box);
                } else {
                    fromTo[start][end] = c - truck;
                    totalSum += (c - truck);
                    truck += (c - truck);
//                    System.out.println("실을 택배: " + fromTo[start][end]);
                }
            }
        }

        System.out.println(totalSum);
    }
}
