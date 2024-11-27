import java.util.*;

class Solution {
    
    private static class Point {
        public final long x, y;
        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        
        // 모든 교점 찾기
        for(int i = 0; i < line.length; i++) {
            for(int j = i + 1; j < line.length; j++) { // 중복 제거
                Point intersection = intersection(line[i][0], line[i][1], line[i][2], line[j][0], line[j][1], line[j][2]);
                if (intersection != null) {
                    points.add(intersection);
                }
            }
        }
        
        // 최소/최대 좌표 구하기
        Point minimum = getMinimumPoint(points);
        Point maximum = getMaximumPoint(points);
        
        int width = (int) (maximum.x - minimum.x + 1);
        int height = (int) (maximum.y - minimum.y + 1);
        
        // 별 찍기용 맵 초기화
        char[][] map = new char[height][width];
        for (char[] row : map) {
            Arrays.fill(row, '.');
        }

        // 별 찍기
        for (Point p : points) {
            int x = (int) (p.x - minimum.x);
            int y = (int) (maximum.y - p.y);
            map[y][x] = '*';
        }
        
        // 결과 문자열 배열 변환
        String[] result = new String[height];
        for (int i = 0; i < height; i++) {
            result[i] = new String(map[i]);
        }
        
        return result;
    }
    
    private Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
        long denominator = a1 * b2 - a2 * b1;
        if (denominator == 0) return null; // 평행하거나 일치
        
        long xNumerator = b1 * c2 - b2 * c1;
        long yNumerator = a2 * c1 - a1 * c2;
        
        if (xNumerator % denominator != 0 || yNumerator % denominator != 0) return null;
        
        return new Point(xNumerator / denominator, yNumerator / denominator);
    }
    
    private Point getMinimumPoint(List<Point> points) {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;
        
        for (Point p : points) {
            if (p.x < x) x = p.x;
            if (p.y < y) y = p.y;
        }
        
        return new Point(x, y);
    }
    
    private Point getMaximumPoint(List<Point> points) {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;
        
        for (Point p : points) {
            if (p.x > x) x = p.x;
            if (p.y > y) y = p.y;
        }
        
        return new Point(x, y);
    }
}
