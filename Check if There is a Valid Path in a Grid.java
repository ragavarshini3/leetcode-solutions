import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        // Map of valid directions for each type
        Map<Integer, int[][]> map = new HashMap<>();
        map.put(1, new int[][]{{0,-1},{0,1}});
        map.put(2, new int[][]{{-1,0},{1,0}});
        map.put(3, new int[][]{{0,-1},{1,0}});
        map.put(4, new int[][]{{0,1},{1,0}});
        map.put(5, new int[][]{{0,-1},{-1,0}});
        map.put(6, new int[][]{{0,1},{-1,0}});

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int i = curr[0], j = curr[1];

            if(i == m-1 && j == n-1) return true;

            for(int[] d : map.get(grid[i][j])) {
                int ni = i + d[0];
                int nj = j + d[1];

                if(ni < 0 || nj < 0 || ni >= m || nj >= n || visited[ni][nj])
                    continue;

                // Check reverse connection
                for(int[] back : map.get(grid[ni][nj])) {
                    if(ni + back[0] == i && nj + back[1] == j) {
                        visited[ni][nj] = true;
                        q.offer(new int[]{ni,nj});
                    }
                }
            }
        }

        return false;
    }
}