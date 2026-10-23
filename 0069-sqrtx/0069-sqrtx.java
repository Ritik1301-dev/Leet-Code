class Solution {
    public int mySqrt(int x) {
        int i; 
        for( i=1;i<=x/i;i++){
            if(i*i == x) return i;
        }
        return i-1;
    }
}