import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 28; i++) {
            list.add(Integer.parseInt(input.readLine()));
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            if (!list.contains(i)) {
                answer.append(i).append("\n");
            }
        }

        System.out.println(answer);
    }
}
