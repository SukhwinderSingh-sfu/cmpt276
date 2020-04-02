package Model;

import java.util.*;

public class LensManager {
    int cnt;
    private List<Lens> lensobj= new ArrayList<>();
    public void add(Lens lens){
        lensobj.add(lens);
    }
    public void display() {
        cnt = 0;
        for (Lens lens : lensobj) {
            System.out.println(cnt++ + ".\t" + lens);
        }
    }
    public int size(){
        return lensobj.size();
    }
    public double getmaxaperture(int choice){
        Lens result=lensobj.get(choice);
        return result.getMaximum_aperture();


    }
    public int getfocus(int choice){
        Lens result=lensobj.get(choice);
        return result.getFocal_length();
    }



}





