class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int p=0;
        int q =nums.length -1;
        int index = nums.length-1;
        while(p<=q){
            if(nums[p]*nums[p] > nums[q]*nums[q]){
                ans[index] = nums[p]*nums[p];
                p++;
            }
            else{
                ans[index] = nums[q]*nums[q];
                q--;
            }
            index--;
        }    
        return ans;      
    }
}