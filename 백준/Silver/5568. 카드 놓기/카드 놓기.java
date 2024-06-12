import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int k;
    private static Map<String, Integer> map = new HashMap<>();
    private static int[] idxs;
    private static String[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        k = Integer.parseInt(input.readLine());
        nums = new String[n];
        idxs = new int[k];
        for (int i = 0; i < n; i++) {
            nums[i] = input.readLine();
        }
        findPossibleNum(0, new boolean[n]);
        System.out.println(map.size());
    }

    private static void findPossibleNum(int idx, boolean[] visited) {
        if (idx == k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idxs.length; i++) {
                sb.append(nums[idxs[i]]);
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                idxs[idx] = i;
                findPossibleNum(idx + 1, visited);
                visited[i] = false;
            }
        }
    }
}