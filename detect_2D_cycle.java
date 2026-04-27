class Solution {
    int m, n;
    boolean[][] visited;

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int x, int y, int px, int py, char ch) {
        visited[x][y] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && ny >= 0 && nx < m && ny < n && grid[nx][ny] == ch) {

                // If not visited → continue DFS
                if (!visited[nx][ny]) {
                    if (dfs(grid, nx, ny, x, y, ch)) {
                        return true;
                    }
                }
                // If visited and NOT parent → cycle found
                else if (nx != px || ny != py) {
                    return true;
                }
            }
        }
        return false;
    }
}