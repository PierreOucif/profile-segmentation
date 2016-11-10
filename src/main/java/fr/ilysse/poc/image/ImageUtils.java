package fr.ilysse.poc.image;

import fr.ilysse.poc.color.converter.LAB;

import java.util.List;

/**
 * Created by p_poucif on 10/11/2016.
 */
public class ImageUtils {



    public static Long[][] getLABBornes(List<LAB> listOfLABs){
        Long[][] results = new Long[2][2];
        LAB lab = null;
        double xmax = 0;
        double xmin = 0;
        double ymin = 0;
        double ymax = 0;
        for (int i=0;i<listOfLABs.size();i++){
            lab = listOfLABs.get(i);
            if(i==0){
                xmax = xmin = lab.getA();
                ymax = ymin = lab.getB();
            }else{
                xmax=Double.compare(xmax,lab.getA())<0?lab.getA():xmax;
                xmin=Double.compare(xmin,lab.getA())>0?lab.getA():xmin;
                ymax=Double.compare(ymax,lab.getB())<0?lab.getB():ymax;
                ymin=Double.compare(ymin,lab.getA())>0?lab.getB():ymin;
            }
        }
        results[0][0] = Math.round(xmin)-1;
        results[0][1] = Math.round(xmax)+1;
        results[1][0] = Math.round(ymin)-1;
        results[1][1] = Math.round(ymax)+1;
        return results;
    }



    public static void getImageOfLAB(List<LAB> listOfLAB,Long[][] bornes){
        Long xmin = bornes[0][0];
        Long xmax = bornes[0][1];
        Long ymin = bornes[1][0];
        Long ymax = bornes[1][1];

        Long xScale =xmax-xmin;
        Long yScale =ymax-ymin;



    }


}
