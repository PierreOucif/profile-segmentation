package fr.ilysse.poc.image;

import fr.ilysse.poc.color.converter.LAB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                ymin=Double.compare(ymin,lab.getB())>0?lab.getB():ymin;
            }
        }
        results[0][0] = Math.round(xmin-1);
        results[0][1] = Math.round(xmax+1);
        results[1][0] = Math.round(ymin-1);
        results[1][1] = Math.round(ymax+1);
        return results;
    }



    public static Map<String,Object> getXYOfLAB(List<LAB> listOfLAB,Long[][] bornes) throws Exception{
        int xmin = Math.toIntExact(bornes[0][0]);
        int xmax = Math.toIntExact(bornes[0][1]);
        int ymin = Math.toIntExact(bornes[1][0]);
        int ymax = Math.toIntExact(bornes[1][1]);

        int xScale =Math.toIntExact(xmax-xmin);
        int yScale =Math.toIntExact(ymax-ymin);

        LAB[][] labImage = new LAB[xScale][yScale];

        for(LAB lab : listOfLAB){
            int a = Math.toIntExact(Math.round(lab.getA()))+Math.abs(xmin);
            int b = Math.toIntExact(Math.round(lab.getB()))+Math.abs(ymin);
            if(a>=0 && a<=xmax+Math.abs(xmin)){
                if(b>=0 && b<=ymax+Math.abs(ymin)){
                    labImage[a][b]=lab;
                }else{
                    throw new Exception("b value out of the bounds : "+b);
                }
            }else{
                throw new Exception("a value out of the bounds : " +a);
            }
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("xScale",xScale);
        resultMap.put("yScale",yScale);
        resultMap.put("labImage",labImage);
        return resultMap;

    }

    public static BufferedImage getImageFromXYLAB(Map<String,Object> objectsMap){
        BufferedImage image = new BufferedImage((int)objectsMap.get("xScale"),(int)objectsMap.get("yScale"),BufferedImage.TYPE_INT_RGB);
        LAB[][] lab = (LAB[][])objectsMap.get("labImage");
        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);

        for(int x=0;x<(int)objectsMap.get("xScale");x++){
            for(int y=0;y<(int)objectsMap.get("yScale");y++){
                if(lab[x][y]!=null){
                    image.setRGB(x,y,black.getRGB());
                }else{
                    image.setRGB(x,y,white.getRGB());
                }
            }
        }

        return image;

    }


}
