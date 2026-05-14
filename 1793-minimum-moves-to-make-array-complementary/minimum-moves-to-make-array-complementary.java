import java.util.*;

class Solution {
    public int minMoves(int[] nums, int limit) {

        int n = nums.length;
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - i - 1];

            int low = Math.min(a, b);
            int high = Math.max(a, b);

            // 2 moves baseline everywhere
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            // 1 move range
            diff[low + 1] -= 1;
            diff[high + limit + 1] += 1;

            // 0 move range
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        int res = Integer.MAX_VALUE;
        int curr = 0;

        for (int s = 2; s <= 2 * limit; s++) {
            curr += diff[s];
            res = Math.min(res, curr);
        }

        return res;
    }
}