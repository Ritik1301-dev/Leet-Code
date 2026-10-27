class Solution {
    public int majorityElement(int[] arr) {
        int ans = arr[0];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                ans = arr[i];
                count = 1;
            } else {
                if (arr[i] == ans) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return ans;
    }
}