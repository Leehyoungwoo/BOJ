import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static Map<String, Integer> selling = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String s = input.readLine();
            selling.put(s, selling.getOrDefault(s, 0) + 1);
        }
        String bestSeller = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : selling.entrySet()) {
            String title = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount || (count == maxCount && title.compareTo(bestSeller) < 0)) {
                bestSeller = title;
                maxCount = count;
            }
        }

        System.out.println(bestSeller);
    }
}