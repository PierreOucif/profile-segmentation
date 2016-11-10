package fr.ilysse.poc;

import fr.ilysse.poc.clustering.ClusterUtils;
import fr.ilysse.poc.color.converter.ColorConverterData;
import fr.ilysse.poc.color.converter.LAB;
import fr.ilysse.poc.image.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    public static void main(String[] args) throws IOException, URISyntaxException {

        Files.newDirectoryStream(Paths.get(CURRENT_PATH.toURI()),"*.{jpg}").forEach((Path dir)->{
            BufferedImage imageBuffered = null;
            try{
                imageBuffered = ImageIO.read(dir.toFile());
                ColorConverterData colorDatas = new ColorConverterData(imageBuffered);
                ClusterUtils clusterUtils = new ClusterUtils();

                Long[][] LABBornes = ImageUtils.getLABBornes(colorDatas.getListOfLAB());
                ImageUtils.getImageOfLAB(colorDatas.getListOfLAB(),LABBornes);


                Map<LAB,Integer> kMeansMap =clusterUtils.kMeans(3,2,colorDatas.getListOfLAB());
                BufferedImage finalBufferedImage = colorDatas.getImagePostKMeans(kMeansMap);

                JFrame frameInit = new JFrame();
                JLabel labelInit = new JLabel(new ImageIcon(imageBuffered));
                frameInit.setSize(imageBuffered.getWidth(),imageBuffered.getHeight());
                frameInit.add(labelInit);
                frameInit.setVisible(true);

                JFrame frameFinal = new JFrame();
                JLabel labelFinal = new JLabel(new ImageIcon(finalBufferedImage));
                frameFinal.setSize(finalBufferedImage.getWidth(),finalBufferedImage.getHeight());
                frameFinal.add(labelFinal);
                frameFinal.setVisible(true);



            }catch (IOException e){
                System.out.println(e.getMessage());
            }













        });
    }
}
