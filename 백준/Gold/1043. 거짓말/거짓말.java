import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        tokenizer = new StringTokenizer(input.readLine());
        int truthCnt = Integer.parseInt(tokenizer.nextToken());
        int[] truth = new int[truthCnt];
        for (int i = 0; i < truthCnt; i++) {
            truth[i] = Integer.parseInt(tokenizer.nextToken());
        }
        List<int[]> parties = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int partySize = Integer.parseInt(tokenizer.nextToken());
            int[] party = new int[partySize];
            for (int j = 0; j < partySize; j++) {
                party[j] = Integer.parseInt(tokenizer.nextToken());
            }
            parties.add(party);

            for (int j = 1; j < partySize; j++) {
                union(party[0], party[j]);
            }
        }

        if (truthCnt > 0) {
            for (int i = 1; i < truthCnt; i++) {
                union(truth[0], truth[i]);
            }
        }

        int result = 0;
        for (int[] party : parties) {
            boolean canLie = true;
            for (int person : party) {
                if (truthCnt > 0 && find(person) == find(truth[0])) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parent[parentB] = parentA;
        }
    }

    private static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
}