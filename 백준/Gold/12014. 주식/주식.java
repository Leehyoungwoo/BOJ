import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int t;
    private static int n, k;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        t = Integer.parseInt(input.readLine());
        for (int i = 1; i <= t; i++) {
            answer.append("Case #").append(i).append("\n");
            init(input);
            if (list.size() >= k) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }
        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        list = new ArrayList<>();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (list.isEmpty() || num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                list.set(getLowerBound(num), num);
            }
        }
    }

    private static int getLowerBound(int num) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}