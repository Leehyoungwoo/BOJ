import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<Integer> nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(input.readLine()));
        }
        Collections.sort(nums);
    }

    private static void findAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append(nums.get(i)).append("\n");
        }

        System.out.print(answer);
    }
}
