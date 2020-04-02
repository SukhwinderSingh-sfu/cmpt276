package Model;

public class Depth_of_field_calculator {
    double hyperfocal_distance;
    double nearfocal_point;
    double farfocal_point;
    int focus;
    double a;
    double b;
    double coc;
    double depth_of_field;



    public Depth_of_field_calculator(int focus,double a,double b,double coc) {
        hyperfocal_distance=0.0;
        nearfocal_point=0.0;
        farfocal_point=0.0;
        depth_of_field=0.0;
        this.focus=focus;
        this.a=a;
        this.b=b;
        this.coc=coc;


    }

    public double getHyperfocal_distance() {
        hyperfocal_distance= (focus*focus)/(a*coc);
        return hyperfocal_distance;
    }

    public double getNearfocal_point() {
        nearfocal_point=(hyperfocal_distance*b)/(hyperfocal_distance+b-focus);
        return nearfocal_point;
    }

    public double getFarfocal_point(){
        if(b>hyperfocal_distance) {
            farfocal_point = Double.POSITIVE_INFINITY;
        }
        else{
            farfocal_point=(hyperfocal_distance*b)/(hyperfocal_distance-(b-focus));
        }
        return farfocal_point;
    }

    public double getDepth_of_field() {
        depth_of_field=farfocal_point-nearfocal_point;
        return depth_of_field;
    }
}
