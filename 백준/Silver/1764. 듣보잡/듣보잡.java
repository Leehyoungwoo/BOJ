import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n + m; i++) {
            String name = input.readLine();
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        List<String> names = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s) == 2) {
                names.add(s);
            }
        }
        Collections.sort(names);
        System.out.println(names.size());
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
    }
}