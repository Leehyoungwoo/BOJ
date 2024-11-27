import java.util.*;
import java.io.*;

public class Main {
    static char[] king, rock;
    static int n;

    public static void main(String args[]) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bfr.readLine(), " ");
        king = stk.nextToken().toCharArray();
        rock = stk.nextToken().toCharArray();
        n = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            String cmd = bfr.readLine();
            char[] next_king = move(cmd, king);
            if (isInRange(next_king)) {
                if (Arrays.equals(next_king, rock)) {
                    char[] next_rock = move(cmd, rock);
                    if (isInRange(next_rock)) {
                        king = next_king;
                        rock = next_rock;
                    }
                } else {
                    king = next_king;
                }
            }
        }

        System.out.println(String.valueOf(king));
        System.out.println(String.valueOf(rock));
    }

    static Boolean isInRange(char[] a) {
        return a[0] >= 'A' && a[0] <= 'H' && a[1] >= '1' && a[1] <= '8';
    }

    static char[] move(String cmd, char[] target) {
        char[] result = target.clone();
        switch (cmd) {
            case "R": result[0]++; break;
            case "L": result[0]--; break;
            case "B": result[1]--; break;
            case "T": result[1]++; break;
            case "RT": result[0]++; result[1]++; break;
            case "LT": result[0]--; result[1]++; break;
            case "RB": result[0]++; result[1]--; break;
            case "LB": result[0]--; result[1]--; break;
        }
        return result;
    }
}