import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String target = input.readLine();

        if (isCppStyle(target)) {
            System.out.println(convertToJavaStyle(target));
        } else if (isJavaStyle(target)) {
            System.out.println(convertToCppStyle(target));
        } else {
            System.out.println("Error!");
        }
    }

    private static boolean isCppStyle(String target) {
        // Check if the string is in C++ style
        return target.contains("_") && !target.matches(".*[A-Z].*") && !target.startsWith("_") && !target.endsWith("_") && !target.contains("__");
    }

    private static boolean isJavaStyle(String target) {
        // Check if the string is in Java style
        return !target.contains("_") && target.matches("^[a-z][a-zA-Z0-9]*$");
    }

    private static String convertToJavaStyle(String target) {
        StringBuilder sb = new StringBuilder();
        boolean toUpperCase = false;
        for (char ch : target.toCharArray()) {
            if (ch == '_') {
                toUpperCase = true;
            } else {
                if (toUpperCase) {
                    sb.append(Character.toUpperCase(ch));
                    toUpperCase = false;
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    private static String convertToCppStyle(String target) {
        StringBuilder sb = new StringBuilder();
        for (char ch : target.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                sb.append('_').append(Character.toLowerCase(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}