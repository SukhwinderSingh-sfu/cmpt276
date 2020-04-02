package Model;

import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;


import static org.junit.jupiter.api.Assertions.*;

class Depth_of_field_calculatorTest {
    double coc=0.029;
    Lens a1=new Lens("Canon",1.8,50);
    Lens b1=new Lens("Sigma",2.8,200);
    Lens c1=new Lens("Nikon",4,200);


    @Test
    void testgetHyperfocal_distance() {
        Depth_of_field_calculator abc1 = new Depth_of_field_calculator(a1.getFocal_length(),2.8,80000,coc);
        Depth_of_field_calculator abc2 = new Depth_of_field_calculator(b1.getFocal_length(),5.6,1000,coc);
        Depth_of_field_calculator abc3 = new Depth_of_field_calculator(c1.getFocal_length(),14.2,220000,coc);
        assertEquals(30.79,Double.parseDouble(formatM(abc1.getHyperfocal_distance()/1000)));
        assertEquals(246.31,Double.parseDouble(formatM(abc2.getHyperfocal_distance()/1000)));
        assertEquals(97.13,Double.parseDouble(formatM(abc3.getHyperfocal_distance()/1000)));
    }
    @Test
    void testgetNearfocal_Point() {
        Depth_of_field_calculator abc1 = new Depth_of_field_calculator(a1.getFocal_length(),2.8,80000,coc);
        Depth_of_field_calculator abc2 = new Depth_of_field_calculator(b1.getFocal_length(),5.6,1000,coc);
        Depth_of_field_calculator abc3 = new Depth_of_field_calculator(c1.getFocal_length(),14.2,220000,coc);
        abc1.getHyperfocal_distance();
        abc2.getHyperfocal_distance();
        abc3.getHyperfocal_distance();
        assertEquals(22.24,Double.parseDouble(formatM(abc1.getNearfocal_point()/1000)),0.001);
        assertEquals(1,Double.parseDouble(formatM(abc2.getNearfocal_point()/1000)),0.001);
        assertEquals(67.43,Double.parseDouble(formatM(abc3.getNearfocal_point()/1000)),0.001);
    }
    @Test
    void testgetFarfocal_Point() {
        Depth_of_field_calculator abc1 = new Depth_of_field_calculator(a1.getFocal_length(),2.8,80000,coc);
        Depth_of_field_calculator abc2 = new Depth_of_field_calculator(b1.getFocal_length(),5.6,1000,coc);
        Depth_of_field_calculator abc3 = new Depth_of_field_calculator(c1.getFocal_length(),14.2,220000,coc);
        abc1.getHyperfocal_distance();
        abc2.getHyperfocal_distance();
        abc3.getHyperfocal_distance();
        assertEquals("∞",formatM(abc1.getFarfocal_point()/1000));
        assertEquals("1.00",formatM(abc2.getFarfocal_point()/1000));
        assertEquals("∞",formatM(abc3.getFarfocal_point()/1000));
    }
    @Test
    void testgetDepth_of_field() {
        Depth_of_field_calculator abc1 = new Depth_of_field_calculator(a1.getFocal_length(),2.8,80000,coc);
        Depth_of_field_calculator abc2 = new Depth_of_field_calculator(b1.getFocal_length(),5.6,1000,coc);
        Depth_of_field_calculator abc3 = new Depth_of_field_calculator(c1.getFocal_length(),14.2,220000,coc);
        abc1.getHyperfocal_distance();
        abc1.getNearfocal_point();
        abc1.getFarfocal_point();

        abc2.getHyperfocal_distance();
        abc2.getNearfocal_point();
        abc2.getFarfocal_point();

        abc3.getHyperfocal_distance();
        abc3.getNearfocal_point();
        abc3.getFarfocal_point();
        assertEquals("∞",formatM(abc1.getDepth_of_field()/1000));
        assertEquals("0.01",formatM(abc2.getDepth_of_field()/1000));
        assertEquals("∞",formatM(abc3.getDepth_of_field()/1000));
    }
   private String formatM(double distanceInM) {
       DecimalFormat df = new DecimalFormat("0.00");
       return df.format(distanceInM);
   }
}