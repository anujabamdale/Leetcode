import java.util.*;

class Solution {
    public boolean isGood(int[] nums) {

        int n = nums.length;
        int max = 0;

        for (int x : nums) {
            max = Math.max(max, x);
        }

        // length must be max + 1
        if (n != max + 1) {
            return false;
        }

        int[] freq = new int[max + 1];

        for (int x : nums) {
            if (x < 1 || x > max) return false;
            freq[x]++;
        }

        // check 1 to max-1 appear exactly once
        for (int i = 1; i < max; i++) {
            if (freq[i] != 1) return false;
        }

        // max must appear exactly twice
        return freq[max] == 2;
    }
}