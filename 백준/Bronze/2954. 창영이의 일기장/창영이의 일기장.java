import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String encoded = input.readLine();

        String patternStr = "([aeiou])p\\1";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(encoded);
        String decoded = matcher.replaceAll("$1");

        System.out.println(decoded);
    }
}