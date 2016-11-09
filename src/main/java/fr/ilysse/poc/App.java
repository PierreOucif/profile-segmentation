package fr.ilysse.poc;

import fr.ilysse.poc.clustering.ClusterUtils;
import fr.ilysse.poc.color.converter.ColorConverterData;
import fr.ilysse.poc.color.converter.LAB;

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
                Map<LAB,Integer> kMeansMap =clusterUtils.kMeans(3,2,colorDatas.getListOfLAB());
                BufferedImage finalBufferedImage = colorDatas.getImagePostKMeans(kMeansMap);

                JFrame frame = new JFrame();
                JLabel label = new JLabel(new ImageIcon(finalBufferedImage));
                frame.setSize(finalBufferedImage.getWidth(),finalBufferedImage.getHeight());
                frame.add(label);
                frame.setVisible(true);



            }catch (IOException e){
                System.out.println(e.getMessage());
            }













        });
    }
}
