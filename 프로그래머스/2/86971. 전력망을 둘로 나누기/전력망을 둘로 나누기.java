import java.util.*;

class Solution {
    
    private int[] parent;
    private int min = Integer.MAX_VALUE;
    private int tempA = 0;
    private int tempB = 0;
    
    public int solution(int n, int[][] wires) {
        for(int i = 0; i < wires.length; i++) {
            parent = new int[n + 1];
            makeParent();
            makeTree(wires, i);
        }
        
        return min;
    }
    
    private void makeTree(int[][] wires, int idx) {
        tempA = 0;
        tempB = 0;
        for (int i = 0; i < wires.length; i++) {
            if (i == idx) {
                continue;
            }
            if (find(wires[i][0]) != find(wires[i][1])) {
                union(wires[i][0], wires[i][1]);
            }
        }
        for (int i = 0; i<parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
        int first = parent[1];
        for(int i = 1; i < parent.length; i++) {
            if (find(parent[i]) == first) {
                tempA++;
            } else {
                tempB++;
            }
        }
        min = Math.min(min, Math.abs(tempA - tempB));
        System.out.println("min + " + min);
    }
    
    private void makeParent() {
        for(int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }
    
    private int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
    
    private void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot <= bRoot) {
            parent[bRoot] = aRoot;
            return;
        }
        parent[aRoot] = bRoot;
    }
}