class Solution {
    public int mirrorDistance(int n) {
        int original = n;
        int reversed = 0;

        // Reverse the number
        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n = n / 10;
        }

        // Return absolute difference
        return Math.abs(original - reversed);
    }
}