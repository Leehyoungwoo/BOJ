import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            wordSet.add(input.readLine());
        }
        String[] words = wordSet.toArray(new String[0]);
        mergeSort(words, 0, words.length - 1);
        StringBuilder answer = new StringBuilder();
        for (String word : words) {
            answer.append(word).append("\n");
        }
        
        System.out.println(answer);
    }

    private static void mergeSort(String[] words, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;

        mergeSort(words, left, mid);
        mergeSort(words, mid + 1, right);
        merge(words, left, mid, right);
    }

    private static void merge(String[] words, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        String[] leftTemp = new String[n1];
        String[] rightTemp = new String[n2];
        for (int i = 0; i < n1; i++) {
            leftTemp[i] = words[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightTemp[i] = words[mid + 1 + i];
        }
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (compare(leftTemp[i], rightTemp[j]) <= 0) {
                words[k] = leftTemp[i];
                i++;
            } else {
                words[k] = rightTemp[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            words[k] = leftTemp[i];
            i++;
            k++;
        }
        while (j < n2) {
            words[k] = rightTemp[j];
            j++;
            k++;
        }
    }

    private static int compare(String a, String b) {
        if (a.length() != b.length()) {
            return Integer.compare(a.length(), b.length());
        }
        return a.compareTo(b);
    }
}