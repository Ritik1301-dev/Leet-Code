class Solution {
    public int maxArea(int[] height) {

        int lP = 0, rP = height.length - 1;
        int maxWater = 0;

        while (lP < rP) {

            int width = rP - lP;
            int h = Math.min(height[lP], height[rP]);

            int currentWater = width * h;
            maxWater = Math.max(maxWater, currentWater);

            if (height[lP] < height[rP]) {
                lP++;
            } else {
                rP--;
            }
        }

        return maxWater;
    }
}