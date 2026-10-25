class Solution {
    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3 ) return false;
        boolean up = false , down = false;
         for(int i=1;i<arr.length;i++){
            if(arr[i] == arr[i-1])  return false;
            if(!down){
                if(arr[i] > arr[i-1]) up = true;
                else{
                    if(!up) return false;
                    down = true;
                }
            }
            else{
                if(arr[i-1] <= arr[i]) return false;
            }

         }
         return up && down;
    }
}