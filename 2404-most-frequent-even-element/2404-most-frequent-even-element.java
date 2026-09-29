class Solution {
    public int mostFrequentEven(int[] nums) {
        HashMap <Integer,Integer> map = new HashMap<>();
         for(int i=0;i<nums.length;i++){
            if(nums[i] % 2 == 0 ){
                map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            }
        }
        int mostFreq = 0, ans = -1;
        for(int ele :map.keySet()){
            if(map.get(ele) > mostFreq){
                mostFreq = map.get(ele);
                ans = ele;
            }else if(map.get(ele) == mostFreq){
                ans = Math.min(ele , ans );
            }
        }
        return ans;
    }
}