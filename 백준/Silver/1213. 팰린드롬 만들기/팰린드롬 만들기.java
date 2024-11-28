import java.util.*;
import java.io.*;

public class Main {

    private static String name;
    private static int[] freq;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int oddCount = 0;
        char middleChar = '\0';
        for(int i = 0; i < 26; i++) {
            if(freq[i] % 2 == 1) {
                oddCount++;
                middleChar = (char)(i + 'A');
            }
        }
        
        if (oddCount > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }
        
        StringBuilder leftHalf = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                leftHalf.append((char) (i + 'A'));
            }
        }
        
        StringBuilder palindrome = new StringBuilder(leftHalf);
        if (middleChar != '\0') {
            palindrome.append(middleChar);
        }
        palindrome.append(leftHalf.reverse());

        System.out.println(palindrome);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        name = input.readLine();
        freq = new int[26];

        for(char c : name.toCharArray()) {
            freq[c - 'A']++;
        }
    }
}