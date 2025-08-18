import java.io.*;
import java.util.*;

public class Main {

    private static int[] num = new int[8];

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < 8; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        int gap = num[0] - num[1];
        boolean flag = false;
        for (int i = 2; i < 8; i++) {
            if (num[i - 1] - num[i] != gap) {
                System.out.println("mixed");
                return;
            } else {
                gap = num[i - 1] - num[i];
                if (gap == 1) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }

        if (flag) {
            System.out.println("descending");
        } else {
            System.out.println("ascending");
        }
    }
}
