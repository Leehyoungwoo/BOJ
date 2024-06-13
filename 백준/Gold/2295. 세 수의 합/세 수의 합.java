import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(input.readLine()));
        }
        List<Integer> sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum.add(list.get(i) + list.get(j));
            }
        }
        Collections.sort(sum);
        Collections.sort(list);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int z = list.get(i) - list.get(j);
                if (Collections.binarySearch(sum, z) >= 0) {
                    System.out.println(list.get(i));
                    return;
                }
            }
        }
    }
}