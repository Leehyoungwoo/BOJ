import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" ");
            if (s[1].equals("enter")) {
                map.put(s[0], map.getOrDefault(s[0], 0) + 1);
            }
            if (s[1].equals("leave")) {
                map.remove(s[0]);
            }
        }
        List<String> list = new ArrayList<>();
        for (String s : map.keySet()) {
            list.add(s);
        }

        Collections.sort(list, Collections.reverseOrder());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            answer.append(list.get(i)).append("\n");
        }
        System.out.println(answer);
    }
}