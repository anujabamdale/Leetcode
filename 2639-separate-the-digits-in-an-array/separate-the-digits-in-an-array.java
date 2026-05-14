import java.util.*;

class Solution {
    public int[] separateDigits(int[] nums) {

        List<Integer> res = new ArrayList<>();

        for (int num : nums) {

            // store digits in reverse first
            List<Integer> temp = new ArrayList<>();

            while (num > 0) {
                temp.add(num % 10);
                num /= 10;
            }

            // add in correct order
            for (int i = temp.size() - 1; i >= 0; i--) {
                res.add(temp.get(i));
            }
        }

        // convert to array
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }
}