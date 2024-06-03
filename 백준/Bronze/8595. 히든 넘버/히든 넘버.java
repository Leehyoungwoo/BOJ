import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(input.readLine());
        String word = input.readLine();
        long answer = findHiddenSum(word);
        System.out.println(answer);
    }

    private static long findHiddenSum(String word) {
        String regexp = "\\d{1,6}";
        Matcher matcher = Pattern.compile(regexp).matcher(word);
        long sum = 0;
        while (matcher.find()) {
            int hidden = Integer.parseInt(matcher.group());
            sum += hidden;
        }
        return sum;
    }
}