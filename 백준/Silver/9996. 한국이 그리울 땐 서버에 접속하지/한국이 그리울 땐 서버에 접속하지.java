import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int n = Integer.parseInt(input.readLine());
        String regex = input.readLine();
        String[] tokens = regex.split("\\*");
        String pattern = "^" + tokens[0] + ".*" + tokens[1] + "$";
        Pattern p = Pattern.compile(pattern);
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            if (p.matcher(line).find()) {
                answer.append("DA").append("\n");
            } else {
                answer.append("NE").append("\n");
            }
        }

        System.out.println(answer);
    }
}