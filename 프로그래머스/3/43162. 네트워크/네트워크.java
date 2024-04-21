import java.util.*;

class Solution {

    private int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        System.out.println(Arrays.toString(parent));

        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                int a = computers[i][j];
                if(i == j) {
                    continue;
                }
                if (a == 1) {
                    if (find(i) != find(j)) {
                        union(i, j);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(parent));
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < parent.length; i++) {
            set.add(find(i));
        }

        return set.size();
    }

    public int find(int x) {
        if(parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        int xP = find(x);
        int yP = find(y);
        if(xP <= yP) {
            parent[yP] = xP;
            return;
        }
        parent[xP] = yP;
    }
}