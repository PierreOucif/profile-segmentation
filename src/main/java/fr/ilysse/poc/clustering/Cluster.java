package fr.ilysse.poc.clustering;

import fr.ilysse.poc.color.converter.LAB;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilysse on 09/11/2016.
 */
public class Cluster {
    private Integer n = 0;
    private LAB meanLAB;

    public Cluster(){
        this.meanLAB = new LAB();
    }

    public void add(LAB lab){
        meanLAB.setA(lab.getA()+meanLAB.getA());
        meanLAB.setB(lab.getB()+meanLAB.getB());
        n++;
    }

    public LAB getMeanLAB(){
        if(n!=0) {
            meanLAB.setA(meanLAB.getA()/n);
            meanLAB.setB(meanLAB.getB()/n);
            return meanLAB;
        }else{
            return null;
        }
    }
}
