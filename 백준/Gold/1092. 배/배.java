import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static Integer[] crain; // 내림차순 정렬을 위해 Integer
    private static List<Integer> boxes;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        crain = new Integer[n];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            crain[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int m = Integer.parseInt(input.readLine());
        boxes = new ArrayList<>();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(tokenizer.nextToken()));
        }

        Arrays.sort(crain, Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());
    }

    private static void findAnswer() {
        if (boxes.get(0) > crain[0]) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        while (!boxes.isEmpty()) {
            int boxIdx = 0;

            for (int i = 0; i < n; i++) {
                while (boxIdx < boxes.size()) {
                    if (crain[i] >= boxes.get(boxIdx)) {
                        boxes.remove(boxIdx);
                        break;
                    } else {
                        boxIdx++;
                    }
                }
            }

            time++;
        }

        System.out.println(time);
    }
}
