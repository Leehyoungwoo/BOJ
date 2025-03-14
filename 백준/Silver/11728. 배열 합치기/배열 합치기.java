import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<Integer> list = new ArrayList<>();
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        list.sort(Integer::compareTo);
        for(Integer num : list) {
            answer.append(num).append(" ");
        }
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(tokenizer.nextToken()));
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            list.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }
}
