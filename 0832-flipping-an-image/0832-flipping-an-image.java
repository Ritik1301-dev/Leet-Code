class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for(int i=0;i<image.length;i++){
         int p=0 , q=image[i].length-1;
         while(p<q){
            int temp = image[i][p];
            image[i][p] = image[i][q];
            image[i][q] = temp;
            p++;q--;
         }
        }
    for(int i=0;i<image.length;i++){
        for(int j=0;j<image[i].length;j++){
            image[i][j] = image[i][j]==1? 0:1;
        }
    }
    return image;
    }  
}