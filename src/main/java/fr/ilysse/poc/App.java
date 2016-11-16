package fr.ilysse.poc;

import fr.ilysse.poc.clustering.ClusterUtils;
import fr.ilysse.poc.color.converter.ColorConverterData;
import fr.ilysse.poc.color.converter.LAB;
import fr.ilysse.poc.image.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class App {
    private static final URL CURRENT_PATH = App.class.getClassLoader().getResource(".");

    private App() {
    }

    @SuppressWarnings({"all"})
    public static void main(String[] args) throws Exception, IOException, URISyntaxException {

        Files.newDirectoryStream(Paths.get(CURRENT_PATH.toURI()),"*.{jpg}").forEach((Path dir)->{
            BufferedImage imageBuffered = null;
            try{
                imageBuffered = ImageIO.read(dir.toFile());
                ColorConverterData colorDatas = new ColorConverterData(imageBuffered);
                ClusterUtils clusterUtils = new ClusterUtils();

                clusterUtils.executeKMeans(3,3,colorDatas.getListOfLAB());



            }catch (Exception e){
                System.out.println(e.getMessage());
            }













        });
    }
}
