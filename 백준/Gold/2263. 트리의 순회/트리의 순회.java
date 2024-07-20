import java.io.*;
import java.util.*;

public class Main {
    private static int[] inorder, postorder;
    private static Map<Integer, Integer> inorderIndexMap;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int n = Integer.parseInt(input.readLine());
        inorder = new int[n];
        postorder = new int[n];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(input.readLine().trim());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(tokenizer.nextToken());
        }

        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        StringBuilder answer = new StringBuilder();
        buildPreorder(0, n - 1, 0, n - 1, answer);
        System.out.println(answer);
    }

    private static void buildPreorder(int inStart, int inEnd, int postStart, int postEnd, StringBuilder answer) {
        if (inStart > inEnd || postStart > postEnd) return;

        int rootValue = postorder[postEnd];
        answer.append(rootValue).append(" ");

        int rootIndex = inorderIndexMap.get(rootValue);
        int leftSize = rootIndex - inStart;

        buildPreorder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1, answer);
        buildPreorder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1, answer);
    }
}