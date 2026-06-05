import java.util.*;

class Solution {

    private char[] digits;
    private long[][][][][] memoCnt;
    private long[][][][][] memoSum;
    private boolean[][][][][] seen;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n <= 0) return 0;

        digits = Long.toString(n).toCharArray();
        int len = digits.length;

        memoCnt = new long[len + 1][2][11][11][2];
        memoSum = new long[len + 1][2][11][11][2];
        seen = new boolean[len + 1][2][11][11][2];

        Result res = dfs(0, true, false, -1, -1);
        return res.sum;
    }

    private Result dfs(int pos, boolean tight, boolean started,
                       int prev2, int prev1) {

        if (pos == digits.length) {
            return new Result(1, 0);
        }

        int p2 = prev2 + 1; // -1..9 -> 0..10
        int p1 = prev1 + 1;

        if (!tight && seen[pos][started ? 1 : 0][p2][p1][0]) {
            return new Result(
                    memoCnt[pos][started ? 1 : 0][p2][p1][0],
                    memoSum[pos][started ? 1 : 0][p2][p1][0]
            );
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextTight = tight && (d == limit);

            if (!started && d == 0) {
                Result sub = dfs(pos + 1, nextTight, false, -1, -1);
                totalCnt += sub.cnt;
                totalSum += sub.sum;
            } else if (!started) {
                Result sub = dfs(pos + 1, nextTight, true, -1, d);
                totalCnt += sub.cnt;
                totalSum += sub.sum;
            } else {
                long add = 0;

                if (prev2 != -1) {
                    boolean peak = prev1 > prev2 && prev1 > d;
                    boolean valley = prev1 < prev2 && prev1 < d;
                    if (peak || valley) add = 1;
                }

                int np2, np1;

                if (prev2 == -1) {
                    np2 = prev1;
                    np1 = d;
                } else {
                    np2 = prev1;
                    np1 = d;
                }

                Result sub = dfs(pos + 1, nextTight, true, np2, np1);

                totalCnt += sub.cnt;
                totalSum += sub.sum + add * sub.cnt;
            }
        }

        if (!tight) {
            seen[pos][started ? 1 : 0][p2][p1][0] = true;
            memoCnt[pos][started ? 1 : 0][p2][p1][0] = totalCnt;
            memoSum[pos][started ? 1 : 0][p2][p1][0] = totalSum;
        }

        return new Result(totalCnt, totalSum);
    }

    static class Result {
        long cnt;
        long sum;

        Result(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }
}