class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        int INF = 1_000_000_000;
        int[][] dist = new int[26][26];

        // Initialize
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
            }
        }

        // Direct conversions
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd-Warshall
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            char s = source.charAt(i);
            char t = target.charAt(i);

            if (s == t) continue;

            int d = dist[s - 'a'][t - 'a'];
            if (d == INF) return -1;

            ans += d;
        }

        return ans;
    }
}