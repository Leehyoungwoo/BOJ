import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            long m = Long.parseLong(br.readLine());

            long bestDiff = Long.MAX_VALUE;
            int bestX = 0, bestY = 0;

            for (int x = 0; x <= 60; x++) {
                long a = 1L << x;
                for (int y = 0; y <= 60; y++) {
                    long b = 1L << y;
                    long sum = a + b;
                    long diff = Math.abs(sum - m);
                    if (diff < bestDiff) {
                        bestDiff = diff;
                        bestX = x;
                        bestY = y;
                        if (diff == 0) break; 
                    }
                }
                if (bestDiff == 0) break;
            }
            out.append(bestX).append(' ').append(bestY).append('\n');
        }

        System.out.print(out);
    }
}