import java.util.*;

class Solution {

    public int[] solution(long[] numbers) {
        List<Integer> result = new ArrayList<>();

        for (long number : numbers) {
            String binary = Long.toBinaryString(number);
            int treeDepth = calculateDepth(binary.length()); // 트리 깊이 계산
            String paddedBinary = padBinary(binary, treeDepth); // 포화 이진트리로 만들기 위해 패딩

            if (isValidTree(paddedBinary)) {
                result.add(1);
            } else {
                result.add(0);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    // 트리 깊이를 계산 (포화 이진트리로 만들기 위한 깊이)
    private int calculateDepth(int length) {
        int depth = 0;
        while (Math.pow(2, depth) - 1 < length) {
            depth++;
        }
        return depth;
    }

    // 주어진 이진수를 포화 이진트리 크기에 맞게 패딩
    private String padBinary(String binary, int depth) {
        int fullLength = (int)Math.pow(2, depth) - 1; // 포화 이진트리의 총 노드 수
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < fullLength - binary.length(); i++) {
            padded.append("0"); // 필요한 만큼 앞에 0을 붙임
        }
        padded.append(binary);
        return padded.toString();
    }

    // 이진트리가 유효한지 확인하는 함수 (재귀)
    private boolean isValidTree(String tree) {
        return isValidSubtree(tree, 0, tree.length() - 1, true);
    }

    // 서브트리 검증 (루트 노드가 0일 때 하위 서브트리도 모두 0이어야 함)
    private boolean isValidSubtree(String tree, int start, int end, boolean isRootValid) {
        if (start > end) return true;

        int mid = (start + end) / 2;
        char root = tree.charAt(mid);

        if (!isRootValid && root == '1') return false;

        // 루트가 0인 경우, 그 하위 트리들도 루트가 유효하지 않음
        return isValidSubtree(tree, start, mid - 1, root == '1') &&
                isValidSubtree(tree, mid + 1, end, root == '1');
    }
}
