import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower{
    int height;
    int idx;

    public Tower(int height, int idx) {
        this.height = height;
        this.idx = idx;
    }
}
public class Main {

    private static int N;
    private static Stack<Tower> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            Tower tower = new Tower(height, i + 1);

            if (stack.isEmpty()) {
                stack.push(tower);
                sb.append(0).append(" ");
                continue;
            }

            while (true) {
                if (stack.isEmpty()) { //
                    sb.append("0 ");
                    stack.push(tower);
                    break;
                }

                if(stack.peek().height > height) {
                    sb.append(stack.peek().idx).append(" ");
                    stack.push(tower);
                    break;
                }
                else {
                    stack.pop();
                }
            }

        }

        System.out.println(sb);
    }
}