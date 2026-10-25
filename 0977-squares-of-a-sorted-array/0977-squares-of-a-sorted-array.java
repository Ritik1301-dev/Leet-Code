class Solution {
    public int[] sortedSquares(int[] nums) {
        int temp [] = new int[nums.length];
        int i=0 , j=nums.length-1 , k=temp.length-1;

        while(k >=0){
            if(Math.abs(nums[i]) > Math.abs(nums[j])){
                temp[k--] = nums[i]*nums[i++];
            }
            else{
                temp[k--] = nums[j]*nums[j--];
            }
        }
        return temp; 
    }
}