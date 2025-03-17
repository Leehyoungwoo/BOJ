import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<Integer> weight;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        weight = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weight.add(Integer.parseInt(tokenizer.nextToken()));
        }
        // 일단 정렬
        Collections.sort(weight);
    }

    private static void findAnswer() {
        int smallest = 1;
        for(int w : weight) {
            if (w > smallest) {
                break;
            }
            smallest += w;
        }

        System.out.println(smallest);

    }
}