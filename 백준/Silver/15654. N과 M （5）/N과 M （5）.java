import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] arr;

    private static boolean[] used;

    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        Set<Integer> intHashSet = new HashSet<>();

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            intHashSet.add(Integer.parseInt(st.nextToken()));
        }

        int idx = 0;

        for (int num : intHashSet) {
            arr[idx] = num;
            idx++;
        }

        Arrays.sort(arr);

        used = new boolean[N];

        sequence = new int[N];

        dfs(0);
    }

    static void dfs(int idx) {
        if (idx == M) {
            for (int i = 0; i < M; i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(sequence[i]);
            }
            System.out.println();

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                sequence[idx] = arr[i];
                dfs(idx + 1);
                used[i] = false;
                sequence[idx] = 0;
            }
        }
    }
}