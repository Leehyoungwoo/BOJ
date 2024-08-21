import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        List<Integer> array = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            if (array.isEmpty() || array.get(array.size() - 1) < number) {
                array.add(number);
            } else {
                array.set(getLowerBound(number, array), number);
            }
        }

        System.out.println(array.size());
    }

    public static int getLowerBound(int number, List<Integer> array) {
        int left = 0;
        int right = array.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (array.get(mid) < number) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}