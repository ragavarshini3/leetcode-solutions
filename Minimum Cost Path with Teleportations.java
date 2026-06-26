import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int INF = 1_000_000_000;

        int[][][] dp = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[t][i], INF);
            }
        }

        // Without teleport
        dp[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i - 1][j] + grid[i][j]);
                if (j > 0)
                    dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i][j - 1] + grid[i][j]);
            }
        }

        // Group cells by value
        TreeMap<Integer, List<int[]>> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(grid[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        // DP with teleportations
        for (int t = 1; t <= k; t++) {
            int best = INF;

            for (List<int[]> cells : map.values()) {
                for (int[] cell : cells) {
                    best = Math.min(best, dp[t - 1][cell[0]][cell[1]]);
                }
                for (int[] cell : cells) {
                    dp[t][cell[0]][cell[1]] = best;
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0)
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i - 1][j] + grid[i][j]);
                    if (j > 0)
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j - 1] + grid[i][j]);
                }
            }
        }

        int ans = INF;
        for (int t = 0; t <= k; t++) {
            ans = Math.min(ans, dp[t][m - 1][n - 1]);
        }

        return ans;
    }
}