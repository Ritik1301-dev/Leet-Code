class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int srow=0, scol=0, erow =n-1, ecol=n-1;
        int num = 1;

        while(srow<=erow && scol<=ecol){
            //top
            for(int j=scol;j<=ecol;j++){
                matrix[srow][j] = num++;
            }
            //right 
            for(int i=srow+1;i<=erow;i++){
                matrix[i][ecol] = num++;
            }
            //bottom 
            for(int j=ecol-1;j>=scol;j--){
                if(scol == ecol){
                    break;
                  }
                  matrix[erow][j] = num++;
                }
                // left 
                for(int i=erow-1;i>=srow+1;i--){
                  if(srow == erow){
                    break;
                  }
                  matrix[i][scol] = num++;
                }
                srow++; scol++; erow--; ecol--;
    }
    return matrix;
   }
}
