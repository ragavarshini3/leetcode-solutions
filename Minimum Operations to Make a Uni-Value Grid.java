import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Step 1: Flatten grid
        for (int[] row : grid) {
            for (int val : row) {
                list.add(val);
            }
        }
        
        // Step 2: Check feasibility
        int base = list.get(0);
        for (int val : list) {
            if ((val - base) % x != 0) {
                return -1;
            }
        }
        
        // Step 3: Sort
        Collections.sort(list);
        
        // Step 4: Find median
        int median = list.get(list.size() / 2);
        
        // Step 5: Calculate operations
        int operations = 0;
        for (int val : list) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}