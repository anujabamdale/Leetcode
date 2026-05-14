import java.util.*;

class Solution {

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            List<Integer> list = new ArrayList<>();

            int top = layer, left = layer;
            int bottom = m - layer - 1;
            int right = n - layer - 1;

            // top row (left -> right)
            for (int j = left; j <= right; j++) {
                list.add(grid[top][j]);
            }

            // right column (top+1 -> bottom-1)
            for (int i = top + 1; i < bottom; i++) {
                list.add(grid[i][right]);
            }

            // bottom row (right -> left)
            for (int j = right; j >= left; j--) {
                list.add(grid[bottom][j]);
            }

            // left column (bottom-1 -> top+1)
            for (int i = bottom - 1; i > top; i--) {
                list.add(grid[i][left]);
            }

            int len = list.size();
            int rot = k % len;

            // rotate list
            List<Integer> rotated = new ArrayList<>();
            rotated.addAll(list.subList(rot, len));
            rotated.addAll(list.subList(0, rot));

            int idx = 0;

            // put back

            // top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            // right column
            for (int i = top + 1; i < bottom; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            // bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            // left column
            for (int i = bottom - 1; i > top; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}