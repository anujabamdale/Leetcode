import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        // Land -> Water
        ans = Math.min(ans,
                solveOrder(landStartTime, landDuration,
                           waterStartTime, waterDuration));

        // Water -> Land
        ans = Math.min(ans,
                solveOrder(waterStartTime, waterDuration,
                           landStartTime, landDuration));

        return (int) ans;
    }

    private long solveOrder(int[] firstStart, int[] firstDur,
                            int[] secondStart, int[] secondDur) {

        int m = secondStart.length;

        int[][] rides = new int[m][2];
        for (int i = 0; i < m; i++) {
            rides[i][0] = secondStart[i];
            rides[i][1] = secondDur[i];
        }

        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

        int[] starts = new int[m];
        long[] prefMinDur = new long[m];
        long[] suffMinFinish = new long[m];

        for (int i = 0; i < m; i++) {
            starts[i] = rides[i][0];
        }

        prefMinDur[0] = rides[0][1];
        for (int i = 1; i < m; i++) {
            prefMinDur[i] = Math.min(prefMinDur[i - 1], rides[i][1]);
        }

        suffMinFinish[m - 1] =
                (long) rides[m - 1][0] + rides[m - 1][1];

        for (int i = m - 2; i >= 0; i--) {
            suffMinFinish[i] = Math.min(
                    suffMinFinish[i + 1],
                    (long) rides[i][0] + rides[i][1]
            );
        }

        long best = Long.MAX_VALUE;

        for (int i = 0; i < firstStart.length; i++) {
            long endTime = (long) firstStart[i] + firstDur[i];

            int pos = upperBound(starts, (int) endTime);

            // rides with start <= endTime
            if (pos > 0) {
                best = Math.min(best,
                        endTime + prefMinDur[pos - 1]);
            }

            // rides with start > endTime
            if (pos < m) {
                best = Math.min(best,
                        suffMinFinish[pos]);
            }
        }

        return best;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}