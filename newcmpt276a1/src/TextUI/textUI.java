package TextUI;
import Model.*;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;


public class textUI {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard


    public textUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    public void show() {
        double a,b;
        int choice;
        double maxaperture;
        int size;
        do {
            System.out.println("Lens to pick from :");
            manager.display();
            System.out.println("-1 to exit");
            System.out.print(": ");
            choice = in.nextInt();
            size=manager.size();
            if(choice>=0&&choice<size){
                System.out.print("Aperture [the F number ]: ");
                a = in.nextDouble();
                System.out.print("Distance to subject [m] : ");
                b = in.nextDouble();
                b = b * 1000;
                maxaperture=manager.getmaxaperture(choice);
                if(a>=maxaperture){
                    int focus=manager.getfocus(choice);
                    Depth_of_field_calculator dof=new Depth_of_field_calculator(focus,a,b,COC);
                    double  t=dof.getHyperfocal_distance();
                    double near=dof.getNearfocal_point();
                    double far=dof.getFarfocal_point();
                    double cac=dof.getDepth_of_field();
                    System.out.println("In focus: "+formatM(near/1000)+"m  to  "+
                            formatM(far/1000)+"m  [DOF = "+formatM(cac/1000)+"m]");
                    System.out.println("Hyperfocal point:  "+formatM(t/1000)+'m');
                    System.out.println();
                    System.out.println();



                }
                else
                {
                    System.out.println("Aperture should be lesser or equal to maximum aperture");
                }

            }
            else if(choice==-1){

            }
            else {
                System.out.println("wrong choice!!");
            }


        }while(choice!=-1);
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}

