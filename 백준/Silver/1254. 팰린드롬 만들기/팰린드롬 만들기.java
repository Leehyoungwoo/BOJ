import java.util.*;
import java.io.*;

public class Main {

    private static String word;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        word = input.readLine();
    }

    private static void findAnswer() {
        int n = word.length();
        int midLength = n;
        for (int i = 0; i < n; i++) {
            if (isPalindrome(word.substring(i))) {
                midLength = n + i;
                break;
            }
        }

        System.out.println(midLength);
    }

    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}