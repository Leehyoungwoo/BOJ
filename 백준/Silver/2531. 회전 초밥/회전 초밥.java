import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int d;
    private static int k;
    private static int c;
    private static ArrayList<Integer> susi;
    private static Set<Integer> window;
    
    public static void main(String[] args) throws IOException {
        init();
        int answer = findEat();
        System.out.println(answer);
    }

    private static int findEat() {
        int maxNum = 0;
        int[] count = new int[d + 1];
        
        for (int i = 0; i < k; i++) {
            int sushiType = susi.get(i);
            if (count[sushiType] == 0) {
                window.add(sushiType);
            }
            count[sushiType]++;
        }
        
        maxNum = window.size();
        
        for (int i = 1; i <= N; i++) {
            int prevSushi = susi.get(i - 1);
            int newSushi = susi.get((i + k - 1) % N);
            
            count[prevSushi]--;
            if (count[prevSushi] == 0) {
                window.remove(prevSushi);
            }
            
            if (count[newSushi] == 0) {
                window.add(newSushi);
            }
            
            count[newSushi]++;
            
            if (count[c] == 0) {
                maxNum = Math.max(maxNum, window.size() + 1);
            } else {
                maxNum = Math.max(maxNum, window.size());
            }
        }
        
        return maxNum;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        susi = new ArrayList<>();
        window = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            susi.add(a);
        }
    }
}