import java.util.*;

class Solution {
    // 4방향 탐색용
    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public int solution(int[][] land) {
        int n = land.length, m = land[0].length;
        int[][] comp = new int[n][m];          
        List<Integer> compSize = new ArrayList<>();
        List<List<Integer>> compCols = new ArrayList<>();
        
        compSize.add(0);      
        compCols.add(null);  
        
        int compId = 0;
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && comp[i][j] == 0) {
                    compId++;
                    compSize.add(0);
                    compCols.add(new ArrayList<>());
                    boolean[] seenCol = new boolean[m];
                    
                    // BFS 시작
                    dq.clear();
                    dq.offer(new int[]{i, j});
                    comp[i][j] = compId;
                    
                    while (!dq.isEmpty()) {
                        int[] cur = dq.poll();
                        int r = cur[0], c = cur[1];
                        
                        compSize.set(compId, compSize.get(compId) + 1);
                        
                        if (!seenCol[c]) {
                            seenCol[c] = true;
                            compCols.get(compId).add(c);
                        }
                        
                        for (int[] d : DIRS) {
                            int nr = r + d[0], nc = c + d[1];
                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                            if (land[nr][nc] == 1 && comp[nr][nc] == 0) {
                                comp[nr][nc] = compId;
                                dq.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
        
        int[] yield = new int[m];
        for (int id = 1; id <= compId; id++) {
            int size = compSize.get(id);
            for (int col : compCols.get(id)) {
                yield[col] += size;
            }
        }
        
        int answer = 0;
        for (int v : yield) {
            answer = Math.max(answer, v);
        }
        return answer;
    }
}
