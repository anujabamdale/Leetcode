class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        
        long sum = 0;
        long f0 = 0;

        // compute sum and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }

        long max = f0;
        long fk = f0;

        // compute F(1) to F(n-1)
        for (int k = 1; k < n; k++) {
            // element rotated to front is nums[n - k]
            fk = fk + sum - (long) n * nums[n - k];
            max = Math.max(max, fk);
        }

        return (int) max;
    }
}