import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (list.isEmpty() || list.get(list.size() - 1) < num) {
                list.add(num);
            } else {
                list.set(getLowerBound(num), num);
            }
        }
        System.out.println(n - list.size());
    }

    private static int getLowerBound(int num) {
        int left = 0;
        int right = list.size();
        while(left < right) {
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