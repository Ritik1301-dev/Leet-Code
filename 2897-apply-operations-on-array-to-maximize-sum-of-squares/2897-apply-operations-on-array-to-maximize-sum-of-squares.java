class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int n = nums.size();
        long ans = 0;

        int[] bitCount = new int[32];

        // Count how many numbers contain each set bit
        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        // Construct the largest possible numbers, one by one
        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    num |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + num * num) % MOD;
        }

        return (int) ans;
    }
}