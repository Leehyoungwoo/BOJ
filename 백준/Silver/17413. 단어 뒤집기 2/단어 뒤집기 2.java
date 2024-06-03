import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String S = input.readLine();

        Pattern pattern = Pattern.compile("(<[^>]+>|[^<\\s]+|\\s+)");
        Matcher matcher = pattern.matcher(S);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String token = matcher.group();
            if (token.startsWith("<") && token.endsWith(">")) {
                result.append(token);
            } else if (token.trim().isEmpty()) {
                result.append(token);
            } else {
                result.append(new StringBuilder(token).reverse());
            }
        }

        System.out.println(result);
    }
}