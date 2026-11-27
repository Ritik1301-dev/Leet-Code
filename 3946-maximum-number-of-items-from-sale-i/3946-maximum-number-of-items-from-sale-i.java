
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];

        int cheapest = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            cheapest = Math.min(cheapest, items[i][1]);
        }

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            int free = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factor == 0) {
                    free++;
                }
            }

            // First purchase: 0/1 Knapsack
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + free + 1
                );
            }
        }

        int ans = 0;

        // Spend remaining budget on cheapest item
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(
                ans,
                dp[b] + (budget - b) / cheapest
            );
        }

        return ans;
    }
}
