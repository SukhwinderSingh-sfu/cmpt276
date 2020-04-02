package Gamelogic;
import java.util.Random;

import android.widget.Button;

public class logic {

    public boolean[][] distributemines(int total_rows,int total_columns,int n){
        boolean A[][]=new boolean[total_rows][total_columns];
        for(int i=0;i<total_rows;i++){
            for(int j=0;j<total_columns;j++){
                A[i][j]=false;
            }
        }
        Random rand=new Random();
        int k;
        for (int i=0;i<n;i++)
        {
            k=rand.nextInt(total_columns*total_rows);
            if(A[k/total_columns][k%total_columns]){
                i--;
            }
            else{
                A[k/total_columns][k%total_columns]=true;
            }

        }
        return A;


    }

    public int minesrowcolumn(boolean c[][],boolean d[][],int x,int y,int total_row,int total_column){
        int count=0;

        for(int i=0;i<total_column;i++ ){
            if(c[x][i]&&!d[x][i])
            count++;
        }
        for(int i=0;i<total_row;i++){
            if(c[i][y]&&!d[i][y])
                count++;
        }
        if(c[x][y]&&!d[x][y])
            count--;
        return count;
    }


}
