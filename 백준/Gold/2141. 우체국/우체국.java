import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        long[][] villages = new long[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            villages[i][0] = Long.parseLong(tokenizer.nextToken()); 
            villages[i][1] = Long.parseLong(tokenizer.nextToken()); 
        }

        Arrays.sort(villages, Comparator.comparingLong(a -> a[0]));

        long totalPopulation = 0;
        for (int i = 0; i < n; i++) {
            totalPopulation += villages[i][1];
        }

        long halfPopulation = (totalPopulation + 1) / 2;
        long cumulativePopulation = 0;
        long result = 0;

        for (int i = 0; i < n; i++) {
            cumulativePopulation += villages[i][1];
            if (cumulativePopulation >= halfPopulation) {
                result = villages[i][0];
                break;
            }
        }

        System.out.println(result);
    }
}