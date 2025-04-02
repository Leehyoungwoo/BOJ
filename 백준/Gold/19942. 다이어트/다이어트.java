import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static Food[] foods;
    private static List<Integer> comb;
    private static int DAN;
    private static int JI;
    private static int TAN;
    private static int VI;
    private static int minPrice = Integer.MAX_VALUE;
    private static List<Integer> answerList;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        foods = new Food[n];
        comb = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        DAN = Integer.parseInt(tokenizer.nextToken());
        JI = Integer.parseInt(tokenizer.nextToken());
        TAN = Integer.parseInt(tokenizer.nextToken());
        VI = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int dan = Integer.parseInt(tokenizer.nextToken());
            int ji = Integer.parseInt(tokenizer.nextToken());
            int tan = Integer.parseInt(tokenizer.nextToken());
            int vi = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());
            foods[i] = new Food(i + 1, dan, ji, tan, vi, price);
        }
    }

    private static void findAnswer() {
        answerList = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        findMinPrice(0, 0, 0, 0, 0, 0);
        answer.append(minPrice).append("\n");
        for (int i = 0; i < answerList.size(); i++) {
            answer.append(answerList.get(i)).append(" ");
        }
        if (minPrice == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void findMinPrice(int start, int danSum, int jiSum, int tanSum, int viSum, int priceSum) {
        if (danSum >= DAN && jiSum >= JI && tanSum >= TAN && viSum >= VI) {
            if (minPrice > priceSum) {
                minPrice = priceSum;
                answerList.clear();
                answerList.addAll(comb);
            }
            return;
        }

        if (start == n) {
            return;
        }
        
        Food curFood = foods[start];
        comb.add(curFood.idx);
        findMinPrice(start + 1, danSum + curFood.dan, jiSum + curFood.ji, tanSum + curFood.tan, viSum + curFood.vit, priceSum + curFood.price);
        comb.remove(comb.size() - 1);
        findMinPrice(start + 1, danSum, jiSum, tanSum, viSum, priceSum);
    }
}

class Food {
    int idx;
    int dan;
    int ji;
    int tan;
    int vit;
    int price;

    public Food(int idx, int dan, int ji, int tan, int vit, int price) {
        this.idx = idx;
        this.dan = dan;
        this.ji = ji;
        this.tan = tan;
        this.vit = vit;
        this.price = price;
    }
}