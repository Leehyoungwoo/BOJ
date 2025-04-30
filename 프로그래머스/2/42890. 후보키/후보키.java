import java.util.*;

class Solution {
    private List<List<Integer>> candidateKeys = new ArrayList<>();
    private String[][] relation;
    private int rowCount, colCount;

    public int solution(String[][] relation) {
        this.relation = relation;
        this.rowCount = relation.length;
        this.colCount = relation[0].length;

        // 1개 컬럼부터 colCount개 컬럼까지 조합 생성
        for (int size = 1; size <= colCount; size++) {
            dfs(0, size, new ArrayList<>());
        }

        return candidateKeys.size();
    }

    // start: 다음 선택할 컬럼 인덱스의 시작점
    // size: 목표 조합 크기
    // comb: 현재까지 고른 컬럼 인덱스 목록
    private void dfs(int start, int size, List<Integer> comb) {
        // 조합 완성 시
        if (comb.size() == size) {
            // 1) 최소성 검사: 기존 후보키 중 하나가 comb에 완전히 포함되어 있으면 스킵
            for (List<Integer> key : candidateKeys) {
                if (comb.containsAll(key)) {
                    return;
                }
            }
            // 2) 유일성 검사: comb 컬럼 조합으로 모든 튜플이 유일한지 확인
            Set<String> seen = new HashSet<>();
            for (String[] row : relation) {
                StringBuilder sb = new StringBuilder();
                for (int col : comb) {
                    sb.append(row[col]).append('|');
                }
                seen.add(sb.toString());
            }
            if (seen.size() == rowCount) {
                // 유일하다면 새로운 후보키 등록
                candidateKeys.add(new ArrayList<>(comb));
            }
            return;
        }

        // 아직 더 고를 컬럼이 남았으면
        for (int i = start; i < colCount; i++) {
            comb.add(i);
            dfs(i + 1, size, comb);
            comb.remove(comb.size() - 1);
        }
    }
}
