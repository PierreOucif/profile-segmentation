package fr.ilysse.poc.clustering;

import com.google.common.base.Stopwatch;
import fr.ilysse.poc.color.converter.LAB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p_poucif on 03/11/2016.
 */
public class ClusterUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterUtils.class);

    public Map<LAB,Integer> kMeans(int nbCluster, int K, List<LAB> dataToClustered){
        Map<LAB,Integer> kMeansMap = new HashMap<LAB,Integer>();
        List<Cluster> initialeClusters = clustersInitialization(nbCluster,dataToClustered);

        for(int i=0;i<initialeClusters.size();i++){
            List<LAB> clusterListOfLAB = initialeClusters.get(i).getListOfLAB();
            for(LAB lab : clusterListOfLAB){
                kMeansMap.put(lab,i);
            }
        }

        return kMeansMap;
    }

    private List<Cluster> clustersInitialization(int nbCluster,List<LAB> dataToClustered){
        LOGGER.info("Initialization of {} random clusters ...",nbCluster);
        Stopwatch timer = Stopwatch.createUnstarted();

        // Datas initialization => random clusters
        List<Cluster> clusters = new ArrayList<>();
        timer.start();


        for(int i=1; i<=nbCluster;i++){
            Cluster cluster = new Cluster();
            int jMin = Math.round((i-1)*dataToClustered.size()/nbCluster);
            int jMax = Math.round(i*dataToClustered.size()/nbCluster);
            for(int j = jMin;j<jMax;j++) {
                cluster.add(dataToClustered.get(j));
            }
            clusters.add(cluster);
            LOGGER.info("Cluster #{} from {} to {} : a_mean = {} / b_mean = {}",i,jMin,jMax,cluster.getMeanLAB()[0],cluster.getMeanLAB()[1]);
        }
        timer.stop();
        LOGGER.info("{} random clusters were succefully created in {}",clusters.size(),timer);

        return clusters;
    }


}
