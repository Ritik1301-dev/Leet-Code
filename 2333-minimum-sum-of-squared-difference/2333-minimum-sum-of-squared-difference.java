
import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long operations = (long) k1 + k2;
        int n = nums1.length;

        int[] diff = new int[n];
        int maxDiff = 0;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
        }

        // If all differences can become zero
        if (operations >= totalDiff) {
            return 0L;
        }

        int left = 0, right = maxDiff;

        // Find the smallest maximum difference achievable
        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= operations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int limit = left;
        long remaining = operations;
        long answer = 0;

        // Reduce every difference to at most limit
        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            answer += (long) d * d;
        }

        // Use remaining operations to reduce limit to limit - 1
        // on as many elements as possible.
        // The optimal solution has at most one level of difference.
        if (limit > 0) {
            long count = 0;

            for (int d : diff) {
                if (d >= limit) {
                    count++;
                }
            }

            // Each such reduction saves limit^2 - (limit-1)^2
            // = 2*limit - 1.
            long reductions = Math.min(remaining, count);
            answer -= reductions * (2L * limit - 1);
        }

        return answer;
    }
}
