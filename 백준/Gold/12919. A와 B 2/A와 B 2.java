import java.io.*;

public class Main {

    private static String s;
    private static String t;
    private static boolean found = false;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        s = input.readLine();
        t = input.readLine();
    }

    private static void findAnswer() {
        dfs(t);
        System.out.println(found ? 1 : 0);
    }

    private static void dfs(String current) {
        if (found) return;
        
        if (current.length() == s.length()) {
            if (current.equals(s)) {
                found = true;
                return;
            }
        }

        if (current.endsWith("A")) {
            dfs(current.substring(0, current.length() - 1));
        }

        if (current.startsWith("B")) {
            String reversed = new StringBuilder(current).reverse().toString();
            dfs(reversed.substring(0, reversed.length() - 1));
        }
    }
}