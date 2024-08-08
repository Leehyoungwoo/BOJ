import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int l, k, c;
    private static int[] cuts;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        l = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        c = Integer.parseInt(s[2]);
        cuts = new int[k + 2];
        cuts[0] = 0;
        cuts[k + 1] = l;
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= k; i++) {
            cuts[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(cuts);
    }

    private static void findAnswer() {
        int left = 1;
        int right = l;
        int resultLength = l;
        int resultPosition = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int position = isPossible(mid);
            if (position != -1) {
                resultLength = mid;
                resultPosition = position;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(resultLength + " " + resultPosition);
    }

    private static int isPossible(int maxLen) {
        int count = 0;
        int lastCut = l;

        for (int i = k + 1; i >= 1; i--) {
            if (cuts[i] - cuts[i - 1] > maxLen) {
                return -1;
            }
            if (lastCut - cuts[i - 1] > maxLen) {
                lastCut = cuts[i];
                count++;
                if (count > c) {
                    return -1;
                }
            }
        }

        if (count < c) {
            return cuts[1];
        } else {
            return lastCut;
        }
    }
}