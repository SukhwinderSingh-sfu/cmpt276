package Model;

public class Lens {
    private  String make;
    private double maximum_aperture;
    private int focal_length;

    public Lens(String make, double maximum_aperature, int focal_length) {
        this.make = make;
        this.maximum_aperture = maximum_aperature;
        this.focal_length = focal_length;
    }

    public String getMake() {
        return make;
    }

    public double getMaximum_aperture() {
        return maximum_aperture;
    }

    public int getFocal_length() {
        return focal_length;
    }

    @Override
    public String toString() {
        return  make + " \t" +
                + maximum_aperture +"mm \tF"+
               + focal_length;
    }

}

