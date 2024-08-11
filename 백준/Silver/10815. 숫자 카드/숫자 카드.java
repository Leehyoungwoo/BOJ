import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n,m;
    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            map.put(num, 1);
        }
        StringBuilder answer = new StringBuilder();
        m = Integer.parseInt(input.readLine());
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            Object result = map.get(num);
            if (!Objects.isNull(result)) {
                answer.append(1).append(" ");
            } else {
                answer.append(0).append(" ");
            }
        }
        System.out.println(answer);
    }
}