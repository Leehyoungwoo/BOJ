import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer,Integer> freq = new HashMap<>();
        Map<Integer,Integer> firstIdx = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = arr[i];
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            firstIdx.putIfAbsent(x, i);
        }

        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys, (a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);
            if (fa != fb) {
                return fb - fa;
            }
            return firstIdx.get(a) - firstIdx.get(b);
        });

        StringBuilder sb = new StringBuilder();
        for (int k : keys) {
            int cnt = freq.get(k);
            for (int i = 0; i < cnt; i++) {
                sb.append(k).append(' ');
            }
        }
        System.out.println(sb);
    }
}
