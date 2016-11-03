package fr.ilysse.poc.color.converter;

import sun.nio.cs.ext.MacHebrew;

import java.awt.*;

/**
 * Created by p_poucif on 03/11/2016.
 */
public class LAB {

    // D65 standart referent
    private static final double X = 0.950470;
    private static final double Y = 1.0;
    private static final double Z = 1.088830;

    private double L;
    private double A;
    private double B;

    public LAB(Color RGBColor){
        double[] LAB = RGBtoLAB(RGBColor);
        this.L = LAB[0];
        this.A = LAB[1];
        this.B = LAB[2];
    }

    private double[] RGBtoLAB(Color RGBColor){

        double vR = RGBColor.getRed()/255;
        double vG = RGBColor.getGreen()/255;
        double vB = RGBColor.getBlue()/255;

        vR = vR > 0.04045 ? Math.pow((vR+0.055)/1.055,2.4) : vR / 12.95;
        vG = vG > 0.04045 ? Math.pow((vG+0.055)/1.055,2.4) : vG / 12.95;
        vB = vB > 0.04045 ? Math.pow((vB+0.055)/1.055,2.4) : vB / 12.95;

        vR *=100;
        vG *=100;
        vB *=100;

        // RGB to XYZ
        double x = (0.4124564*vR+0.3575761*vG+0.1804375*vB)/X;
        double y = (0.2126729*vR+0.7151522*vG+0.0721750*vB)/Y;
        double z = (0.0193339*vR+0.1191920*vG+0.9503041*vB)/Z;

        x = x > 0.008856 ? Math.pow(x,1.0/3) : 7.787037*x + 4.0/29;
        y = y > 0.008856 ? Math.pow(y,1.0/3) : 7.787037*y + 4.0/29;
        z = z > 0.008856 ? Math.pow(z,1.0/3) : 7.787037*z + 4.0/29;

        double[] LAB = new double[3];
        LAB[0]=116*y-16;
        LAB[1]=500*(x-y);
        LAB[2]=200*(y-z);

        return LAB;

    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double[] getAB(){
        double[] AB = new double[2];
        AB[0] = A;
        AB[1] = B;
        return AB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LAB lab = (LAB) o;

        if (Double.compare(lab.L, L) != 0) return false;
        if (Double.compare(lab.A, A) != 0) return false;
        return Double.compare(lab.B, B) == 0;

    }


}
