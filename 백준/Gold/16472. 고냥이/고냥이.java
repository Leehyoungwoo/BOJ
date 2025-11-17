import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static String talk;
    private static char[] word;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        talk = input.readLine();
        word = talk.toCharArray();
    }

    private static int findAnswer() {
        int kind = 0;
        int[] counts = new int[26];
        int max = 0;
        int left = 0;
        for (int right = 0; right < word.length; right++) {
            char c = word[right];
            if (counts[c - 'a'] == 0) {
                kind++;
            }

            counts[c - 'a']++;

            while (kind > n) {
                char l = word[left];
                counts[l - 'a']--;
                if (counts[l - 'a'] == 0) {
                    kind--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}