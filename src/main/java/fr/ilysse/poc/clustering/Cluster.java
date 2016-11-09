package fr.ilysse.poc.clustering;

import fr.ilysse.poc.color.converter.LAB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilysse on 09/11/2016.
 */
public class Cluster {
    private List<LAB> listOfLAB;
    private double[] meanLAB;

    public Cluster(){
        this.listOfLAB = new ArrayList<>();
        this.meanLAB = new double[2];
    }

    public void add(LAB lab){
        listOfLAB.add(lab);
        meanLAB[0] += lab.getA();
        meanLAB[1] += lab.getB();
    }

    public List<LAB> getListOfLAB(){
        return listOfLAB;
    }

    public double[] getMeanLAB(){
        meanLAB[0] /= listOfLAB.size();
        meanLAB[1] /= listOfLAB.size();
        return meanLAB;
    }
}
