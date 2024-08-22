import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int t;
    private static int n;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        t = Integer.parseInt(input.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(input.readLine());
            list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(input.readLine());
                if (list.isEmpty() || list.get(list.size() - 1) < num) {
                    list.add(num);
                } else {
                    list.set(getLowerBound(num), num);
                }
            }
            answer.append(list.size()).append("\n");
        }
        System.out.println(answer);
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