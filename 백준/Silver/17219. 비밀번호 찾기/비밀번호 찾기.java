import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static Map<String, String> password = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            s = input.readLine().split(" ");
            password.put(s[0], s[1]);
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String site = input.readLine();
            answer.append(password.get(site)).append("\n");
        }

        System.out.println(answer);
    }
}