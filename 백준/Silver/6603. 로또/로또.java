import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String line;
        while (!(line = input.readLine()).equals("0")) {
            init(line);
            findAnswer(answer);
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void init(String line) {
        String[] str = line.split(" ");
        n = str.length;
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(str[i]);
        }
    }

    private static void findAnswer(StringBuilder answer) {
        int[] s = new int[num[0]];
        for (int i = 1; i < num.length; i++) {
            s[i - 1] = num[i];
        }
        // 정렬 되어있댜
        makeLotto(0, 0, new int[6], s, answer);
    }

    private static void makeLotto(int idx, int start, int[] comb, int[] number, StringBuilder answer) {
        if (idx == 6) {
            for (int i = 0; i < comb.length; i++) {
                answer.append(comb[i]).append(" ");
            }
            answer.append("\n");
            return;
        }
        if (start == number.length) {
            return;
        }

        comb[idx] = number[start];
        makeLotto(idx + 1, start + 1, comb, number, answer);
        makeLotto(idx, start + 1, comb, number, answer);
    }
}