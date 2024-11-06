import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String s;
    private static String regex;

    public static void main(String[] args) throws IOException {
        init();
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            count++;
        }
        count+=s.replaceAll(regex,"").length();

        System.out.println(count);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        s = input.readLine();
        regex = "c=|c-|dz=|d-|lj|nj|s=|z=";
    }
}