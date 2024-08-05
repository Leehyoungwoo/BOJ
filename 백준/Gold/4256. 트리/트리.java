import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int t;
    private static int n;
    private static int[] preorder;
    private static int[] inorder;
    private static int[] inorderIndex;
    private static int preIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        t = Integer.parseInt(input.readLine());
        for (int i = 0; i < t; i++) {
            init(input);
            preIndex = 0;
            postOrder(0, n - 1, answer);
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        preorder = new int[n];
        inorder = new int[n];
        inorderIndex = new int[n + 1];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            preorder[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(tokenizer.nextToken());
            inorderIndex[inorder[i]] = i;
        }
    }

    private static void postOrder(int inStart, int inEnd, StringBuilder answer) {
        if (inStart > inEnd) return;

        int rootValue = preorder[preIndex++];
        int rootIndex = inorderIndex[rootValue];

        postOrder(inStart, rootIndex - 1, answer);
        postOrder(rootIndex + 1, inEnd, answer);
        answer.append(rootValue).append(" ");
    }
}