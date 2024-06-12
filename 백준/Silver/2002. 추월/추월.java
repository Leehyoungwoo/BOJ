import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        Map<String, Integer> start = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String car = input.readLine();
            start.put(car, i);
        }
        List<String> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String car = input.readLine();
            out.add(car);
        }
        int overtake = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (start.get(out.get(i)) > start.get(out.get(j))) {
                    overtake++;
                    break;
                }
            }
        }
        System.out.println(overtake);
    }
}