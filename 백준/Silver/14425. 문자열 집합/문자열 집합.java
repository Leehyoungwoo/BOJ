import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < n; i++) {
            list.add(input.readLine());
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            String target = input.readLine();
            if (list.contains(target)) {
                count++;
            }
        }

        System.out.println(count);
    }
}