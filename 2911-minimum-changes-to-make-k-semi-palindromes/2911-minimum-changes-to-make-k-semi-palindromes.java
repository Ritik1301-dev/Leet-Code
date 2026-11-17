class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000;

        int[][] cost = new int[n][n];

        // Precompute minimum changes for every substring
        for (int len = 2; len <= n; len++) {
            for (int start = 0; start + len <= n; start++) {

                int best = INF;

                // Try every proper divisor of len
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;
                    int groups = len / d;

                    // Check each group for palindrome property
                    for (int r = 0; r < d; r++) {
                        int left = start + r;
                        int right = start + r + d * (groups - 1);

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[start][start + len - 1] = best;
            }
        }

        // DP: minimum changes to divide first i characters
        // into p semi-palindromic substrings
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}