package com.thoughtwork.train;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtwork.train.algorithm.AdjacencyTool;
import com.thoughtwork.train.algorithm.TrainSpecificRouteCount;
import com.thoughtwork.train.config.Configuration;
import com.thoughtwork.train.exception.TrainException;
import com.thoughtwork.train.model.TrainGraph;
import com.thoughtwork.train.util.ReadWriteIO;
import com.thoughtwork.train.util.StringsUtil;

public class TrainMain {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrainMain.class);

	public static void main(String[] args) {
		try {
			Configuration.initialize();
			LOG.debug("Configuration Properties: " + Configuration.trainProps.toString());
			TrainGraph trainGraph = TrainGraph.getTrainGraphInstance();
			
			//Integer tt = TrainSpecificRouteCount.distanceCal(StringsUtil.addDash("CEBCDEBC"), trainGraph);
			
			//Question Seris 1
			StringBuilder res = new StringBuilder();
			ReadWriteIO.deleteFile(Configuration.trainProps.getProperty("train.putput.file.question1.path"));
			String res11 = TrainSpecificRouteCount.distanceCalWrapper("A‐B‐C", trainGraph);
			res.append("Output #1:" + res11 + "\r\n");
			String res12 = TrainSpecificRouteCount.distanceCalWrapper("A‐D", trainGraph);
			res.append("Output #2:" + res12 + "\r\n");
			String res13 = TrainSpecificRouteCount.distanceCalWrapper("A‐D‐C", trainGraph);
			res.append("Output #3:" + res13 + "\r\n");
			String res14 = TrainSpecificRouteCount.distanceCalWrapper("A‐E‐B‐C‐D", trainGraph);
			res.append("Output #4:" + res14 + "\r\n");
			String res15 = TrainSpecificRouteCount.distanceCalWrapper("A‐E‐D", trainGraph);
			res.append("Output #5:" + res15 + "\r\n");
			
			List<String> res16List = AdjacencyTool.findRoutesByDeepFirstSearch(trainGraph.getAdjaListMap(), "C", "C");
			Integer res16 = 0;
			for(String route : res16List) {
				if(route.length() <= 4) {
					res16 ++;
				}
			}
			res.append("Output #6:" + res16 + "\r\n");
			
			List<String> res17List = AdjacencyTool.findRoutesByDeepFirstSearchRepetitive(trainGraph.getAdjaListMap(), "A", "C", 4);
			res.append("Output #7:" + res17List.size() + "\r\n");
			
			List<String> res18List = AdjacencyTool.findRoutesByDeepFirstSearch(trainGraph.getAdjaListMap(), "A", "C");
			Integer res18 = TrainSpecificRouteCount.getMininalDistance(res18List, trainGraph);
			res.append("Output #8:" + res18 + "\r\n");
			
			List<String> res19List = AdjacencyTool.findRoutesByDeepFirstSearch(trainGraph.getAdjaListMap(), "B", "B");
			Integer res19 = TrainSpecificRouteCount.getMininalDistance(res19List, trainGraph);
			res.append("Output #9:" + res19 + "\r\n");
			
			List<String> res110List = AdjacencyTool.findRoutesWithMaximalDistance(trainGraph.getAdjaListMap(), "C", "C", 30, trainGraph.getAllEdgeMap());
			res.append("Output #10:" + res110List.size() + "\r\n");
			
			ReadWriteIO.writeFile(Configuration.trainProps.getProperty("train.putput.file.question1.path"), res.toString());
			

		} catch (TrainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
