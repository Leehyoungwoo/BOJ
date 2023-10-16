import java.util.Scanner;

public class Main {

    public static int[] stack;
    public static int top = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        int N = input.nextInt();
        stack = new int[N];

        for (int i = 0; i < N; i++) {
            String str = input.next();

            switch (str) {
                case "push":
                    push(input.nextInt());
                    break;
                case "pop":
                    answer.append(pop()).append('\n');
                    break;
                case "size":
                    answer.append(size()).append('\n');
                    break;
                case "empty":
                    answer.append(isEmpty() ? 1 : 0).append('\n');
                    break;
                case "top":
                    answer.append(peek()).append('\n');
            }
        }
        System.out.println(answer);
    }

    public static void push(int elements) {
        stack[top++] = elements;
    }

    public static int pop() {
        if (isEmpty()) {
            return -1;
        }

        int a = peek();
        stack[--top] = 0;
        return a;
    }

    public static int size() {
        return top;
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static int peek() {
        if (isEmpty()) {
            return -1;
        }
        return stack[top - 1];
    }
}