import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    TreeNode(char val) {
        this.val = val;
    }
}

public class Main {
    static Map<Character, TreeNode> tree = new HashMap<>();
    static StringBuilder preorderResult = new StringBuilder();
    static StringBuilder inorderResult = new StringBuilder();
    static StringBuilder postorderResult = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            char parent = line[0].charAt(0);
            char left = line[1].charAt(0);
            char right = line[2].charAt(0);

            TreeNode parentNode = tree.computeIfAbsent(parent, k -> new TreeNode(parent));
            if (left != '.') {
                parentNode.left = tree.computeIfAbsent(left, k -> new TreeNode(left));
            }
            if (right != '.') {
                parentNode.right = tree.computeIfAbsent(right, k -> new TreeNode(right));
            }
        }

        preorder(tree.get('A'));
        inorder(tree.get('A'));
        postorder(tree.get('A'));

        System.out.println(preorderResult);
        System.out.println(inorderResult);
        System.out.println(postorderResult);
    }

    public static void preorder(TreeNode node) {
        if (node == null) return;
        preorderResult.append(node.val);
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        inorderResult.append(node.val);
        inorder(node.right);
    }

    public static void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        postorderResult.append(node.val);
    }
}