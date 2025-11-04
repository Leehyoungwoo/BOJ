import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<Long> nums;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        nums = new ArrayList<>();
    }

    // 감소하는 수는 제일 커봐야 9876543210 이네
    // 아 0 ~10까지의 수롤 포함 할까 말까로 체크한다?
    // 아무것도 고르지 않을수는 없으니까 2^10 - 1 = 1023
    private static void findAnswer() {
        if (n > 1022) {
            System.out.println(-1);
            return;
        }

        dfs(0, new boolean[10]);
        Collections.sort(nums);

        System.out.println(nums.get(n));
    }

    private static void dfs(int idx, boolean[] visited) {
        if (idx == 10) {
            StringBuilder temp = new StringBuilder();
            for (int i = visited.length - 1; i >= 0; i--) {
                if (visited[i]) {
                    temp.append(i);
                }
            }
            if (temp.toString().equals("")) {
                return;
            }
            nums.add(Long.parseLong(temp.toString()));
            return;
        }
        visited[idx] = true;
        dfs(idx + 1, visited);
        visited[idx] = false;
        dfs(idx + 1, visited);
    }
}