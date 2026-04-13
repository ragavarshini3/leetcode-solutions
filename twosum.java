import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // check if complement exists
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // store current element
            map.put(nums[i], i);
        }

        return new int[]{}; // not needed as solution always exists
    }
}