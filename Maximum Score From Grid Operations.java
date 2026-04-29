class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;

        // Prefix sum
        long[][] S = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                S[j][i + 1] = S[j][i] + grid[i][j];
            }
        }

        long[][] dp = new long[n + 1][n + 1];
        long[][] newDp = new long[n + 1][n + 1];

        for (int i = 1; i < n; i++) {

            // Precompute prefix & suffix max
            long[][] prevMax = new long[n + 1][n + 1];
            long[][] prevSuffixMax = new long[n + 1][n + 2];

            for (int prev = 0; prev <= n; prev++) {

                // prefix max
                long best = Long.MIN_VALUE;
                for (int k = 0; k <= n; k++) {
                    long val = dp[prev][k] -
                        Math.max(0, S[i - 1][k] - S[i - 1][prev]);
                    best = Math.max(best, val);
                    prevMax[prev][k] = best;
                }

                // suffix max
                best = Long.MIN_VALUE;
                for (int k = n; k >= 0; k--) {
                    best = Math.max(best, dp[prev][k]);
                    prevSuffixMax[prev][k] = best;
                }
            }

            // transition
            for (int curr = 0; curr <= n; curr++) {
                for (int prev = 0; prev <= n; prev++) {

                    if (curr <= prev) {
                        newDp[curr][prev] =
                            prevSuffixMax[prev][0] +
                            (S[i][prev] - S[i][curr]);
                    } else {
                        long val1 = prevSuffixMax[prev][curr];
                        long val2 = prevMax[prev][curr] +
                            (S[i - 1][curr] - S[i - 1][prev]);

                        newDp[curr][prev] = Math.max(val1, val2);
                    }
                }
            }

            // move dp
            long[][] temp = dp;
            dp = newDp;
            newDp = temp;
        }

        long ans = 0;

        for (int prev = 0; prev <= n; prev++) {
            ans = Math.max(ans, dp[0][prev]);
            ans = Math.max(ans, dp[n][prev]);
        }

        return ans;
    }
}