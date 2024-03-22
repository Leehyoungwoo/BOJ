import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[][] map = new int[n][n];
        fillMap(map, arr1);
        fillMap(map, arr2);
                        // printMap(map);
        char[][] wallMap = new char[n][n];
        findWall(map, wallMap);
        // string 화
        for (int i = 0; i < n; i++) {
            answer[i] = new String(wallMap[i]);
        }
        return answer;
    }
    
    public void fillMap(int[][] map, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int target = arr[i];
            for (int j = arr.length - 1; j >= 0; j--) {
                int divide = (int) Math.pow(2, j);
                if (target >= divide) {
                   target-=divide;
                    map[i][arr.length - 1-j]++;
                }
            }
        }
    }
    
    public void findWall(int[][] map, char[][] wallMap) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    wallMap[i][j] = ' ';
                    continue;
                }
                 wallMap[i][j] = '#';
            }
        }
    }
    
    public void printMap(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}