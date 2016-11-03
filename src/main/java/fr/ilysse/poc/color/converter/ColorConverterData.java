package fr.ilysse.poc.color.converter;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by p_poucif on 03/11/2016.
 */
public class ColorConverterData {
    private static final Logger LOGGER = LoggerFactory.getLogger(ColorConverterData.class);
    private Map<LAB,Color> RGBMappedToLAB;
    private List<LAB> listOfLAB;

    private void initializeColorDatas(BufferedImage image){

        LOGGER.info("Start initialization of the color datas ...");
        final Stopwatch timer = Stopwatch.createUnstarted();

        Map<LAB,Color> vRGBMappedToLAB = new HashMap<>();
        List<LAB> vListOfLAB = new ArrayList<>();

        timer.start();
        for(int w=0;w<image.getWidth();w++){
            for(int h=0;h<image.getHeight();h++){
                Color colorWH = new Color(image.getRGB(w,h));
                LAB LABWH = new LAB(colorWH);
                vRGBMappedToLAB.put(LABWH,colorWH);
                vListOfLAB.add(LABWH);
            }
        }
        RGBMappedToLAB=vRGBMappedToLAB;
        listOfLAB=vListOfLAB;
        timer.stop();
        LOGGER.info("End of the Initialization in {}",timer);
    }

    public ColorConverterData(BufferedImage image){
        initializeColorDatas(image);
    }

    public Map<LAB,Color> getRGBMappedToLAB(){
        return RGBMappedToLAB;
    }

    public List<LAB> getListOfLAB(){
        return listOfLAB;
    }
}
