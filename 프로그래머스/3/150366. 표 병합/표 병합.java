import java.awt.*;
import java.util.*;
import java.util.List;

class Solution {

    private final Cell[][] excel = new Cell[50][50];
    private final List<String> prints = new ArrayList<>();

    public String[] solution(String[] commands) {
        initExcel();
        for (String command : commands) runCommand(command.split(" "));
        return prints.toArray(String[]::new);
    }

    private void runCommand(String[] command) {
        String type = command[0];
        if (type.equals("UPDATE")) {
            if (command.length == 4) {
                int row = Integer.parseInt(command[1]) - 1;
                int col = Integer.parseInt(command[2]) - 1;
                update(row, col, command[3]);
            } else update(command[1], command[2]);
        } else if (type.equals("MERGE")) {
            int r1 = Integer.parseInt(command[1]) - 1;
            int c1 = Integer.parseInt(command[2]) - 1;
            int r2 = Integer.parseInt(command[3]) - 1;
            int c2 = Integer.parseInt(command[4]) - 1;
            merge(r1, c1, r2, c2);
        } else if (type.equals("UNMERGE")) {
            int row = Integer.parseInt(command[1]) - 1;
            int col = Integer.parseInt(command[2]) - 1;
            unmerge(row, col);
        } else {
            int row = Integer.parseInt(command[1]) - 1;
            int col = Integer.parseInt(command[2]) - 1;
            print(row, col);
        }
    }

    private void update(String fromValue, String toValue) {
        for (Cell[] cells : excel)
            for (Cell cell : cells)
                if (cell.value.equals(fromValue)) cell.update(toValue);
    }

    private void update(int row, int col, String value) {
        excel[row][col].update(value);
    }

    private void merge(int row1, int col1, int row2, int col2) {
        Cell cell1 = excel[row1][col1];
        Cell cell2 = excel[row2][col2];
        if (!cell1.equals(cell2)) {
            if (cell1.value.equals("")) cell1.value = cell2.value;
            for (Point cellPoint : cell2.cellPoints) {
                excel[cellPoint.x][cellPoint.y] = cell1;
                cell1.cellPoints.add(cellPoint);
            }
        }
    }

    private void unmerge(int row, int col) {
        Cell cell = excel[row][col];
        for (Point cellPoint : cell.cellPoints)
            excel[cellPoint.x][cellPoint.y] = new Cell(cellPoint.x, cellPoint.y);
        excel[row][col] = new Cell(row, col);
        excel[row][col].value = cell.value;
    }

    private void print(int row, int col) {
        Cell cell = excel[row][col];
        if (cell.value.equals("")) prints.add("EMPTY");
        else prints.add(cell.value);
    }

    private void initExcel() {
        for (int r = 0; r < 50; r++)
            for (int c = 0; c < 50; c++)
                excel[r][c] = new Cell(r, c);
    }
}
class Cell {

    String value = "";
    final List<Point> cellPoints = new ArrayList<>();

    public Cell(int r, int c) {
        cellPoints.add(new Point(r, c));
    }

    public void update(String value) {
        this.value = value;
    }
}
