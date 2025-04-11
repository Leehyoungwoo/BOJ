import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int testcase;
    private static String first;
    private static String second;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testcase = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < testcase; i++) {
            init(input);
            String sum = findAnswer();
            answer.append(sum).append("\n");
        }

        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        first = tokenizer.nextToken();
        second = tokenizer.nextToken();
    }

    private static String findAnswer() {
        int fIdx = first.length() - 1;
        int sIdx = second.length() - 1;
        int over = 0;
        String sum = new String();
        while (fIdx >= 0 && sIdx >= 0) {
            int f = first.charAt(fIdx) - '0';
            int s = second.charAt(sIdx) - '0';
            if (f + s + over >= 2) {
                sum = (char) ((f + s + over - 2) + '0') + sum;
                over = 1;
            } else {
                sum = (char) ((f + s + over) + '0') + sum;
                over = 0;
            }
            fIdx--;
            sIdx--;
        }

        sum = calculateLeft(fIdx, sIdx, over, sum);
        sum = sum.replaceFirst("^0+(?!$)", "");

        return sum;
    }

    private static String calculateLeft(int fIdx, int sIdx, int over, String sum) {
        if (fIdx >= 0) {
            while (fIdx >= 0) {
                int f = first.charAt(fIdx) - '0';
                sum = (char) (((f + over) % 2) + '0') + sum;
                over = (f + over) / 2;
                fIdx--;
            }
        } else if (sIdx >= 0) {
            while (sIdx >= 0) {
                int s = second.charAt(sIdx) - '0';
                sum = (char) (((s + over) % 2) + '0') + sum;
                over = (s + over) / 2;
                sIdx--;
            }
        }

        if (over == 1) {
            sum = (char) (over + '0') + sum;
        }

        return sum;
    }
}
