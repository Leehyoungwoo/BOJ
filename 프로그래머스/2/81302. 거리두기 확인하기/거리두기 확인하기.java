import java.util.*;

class Solution {
    
    private final int[][] direction = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = findAnswer(places[i]);
        }
        return answer;
    }
    
    private int findAnswer(String[] place) {
        char[][] map = new char[place.length][place.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = place[i].toCharArray();
        }
        
        if (isGoodDistance(map)) {
            return 1;
        }
        
        return 0;
    }
    
    private boolean isGoodDistance(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                if (map[i][j] == 'P') {
                    if (!isDistance2ToAllPeople(i, j, map)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean isDistance2ToAllPeople(int r, int c, char[][] map) {
        Queue<int[]> que = new LinkedList<>();
        int size = map.length;
        boolean[][] visited = new boolean[size][size];
        que.offer(new int[] {r, c, 0});
        visited[r][c] = true;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int distance = cur[2];
            if (distance >= 2) {
                continue;
            }
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (isInRange(nextR, nextC, size) && map[nextR][nextC] != 'X' &&
                   !visited[nextR][nextC]) {
                    if (map[nextR][nextC] == 'P') {
                        return false;
                    }
                    que.offer(new int[] {nextR, nextC, distance + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }
        return true;
    }
    
    private boolean isInRange(int r, int c, int n) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}