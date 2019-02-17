package com.thoughtwork.train.model;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtwork.train.algorithm.AdjacencyTool;
import com.thoughtwork.train.config.Configuration;
import com.thoughtwork.train.util.ReadWriteIO;
import com.thoughtwork.train.util.StringsUtil;

/**
 * 用来存储全局数据
 * @author wangxiao
 *
 */
public class TrainGraph implements Graph{
	
	//All nodes byName
	private Map<String, Node> allNodeMap = new HashMap<String, Node>();
	
	//All edges byName
	private Map<String, Edge> allEdgeMap = new HashMap<String, Edge>();
	
	//邻接表定义
	private Map<String, AdjacencyNode> adjaListMap = new HashMap<String, AdjacencyNode>();

	
	private static final Logger LOG = LoggerFactory.getLogger(TrainGraph.class);
	
	private static TrainGraph trainGraphInstance;
	
	private TrainGraph() {
		File inputFile = new File(Configuration.trainProps.getProperty("train.input.file.path"));
		//java.net.URL t = TrainGraph.class.getResource("input.txt");
		LOG.debug("Input File Path: " + inputFile.getPath());
		String inputStr = ReadWriteIO.readFile(inputFile.getPath());
		LOG.debug("Input Content: " + inputStr);
		
		Integer stationIndexLength = Integer.valueOf(Configuration.trainProps.getProperty("train.node.encoding.length"));
		
		if(!StringsUtil.isEmpty(inputStr)) {
			for(String item : inputStr.split(",")) {
				item = item.trim();
				String startStation = item.substring(0, stationIndexLength);
				String endStation = item.substring(stationIndexLength, 2 * stationIndexLength);
				String track = startStation + endStation;
				Integer distance = Integer.valueOf(item.substring(2 * stationIndexLength, item.length()));
				
				// Add Station
				if(!allNodeMap.containsKey(startStation)) {
					allNodeMap.put(startStation, new TrainStationNode(startStation, startStation, 2));
				}
				if(!allNodeMap.containsKey(endStation)) {
					allNodeMap.put(endStation, new TrainStationNode(endStation, endStation, 2));
				}
				// Add Edge
				if(!allEdgeMap.containsKey(track)) {
					allEdgeMap.put(track, new TrainTrackEdge(track, track, startStation, endStation, distance, 1 * distance));
				}
				
				//初始化邻接表
				if(adjaListMap.containsKey(startStation)) {
					AdjacencyNode last = AdjacencyTool.getLast(adjaListMap.get(startStation));
					AdjacencyNode next = new AdjacencyNode(endStation, distance, 1 * distance);
					last.setNextNode(next);
				} else {
					AdjacencyNode first = new AdjacencyNode(startStation);
					AdjacencyNode second = new AdjacencyNode(endStation, distance, 1 * distance);
					first.setNextNode(second);
					adjaListMap.put(startStation, first);
				}
			}
		}
		LOG.debug("TrainGraph initialized completed.");
	}
	
	

	public static TrainGraph getTrainGraphInstance() {
		if(trainGraphInstance == null) {
			trainGraphInstance = new TrainGraph();
		}
		return trainGraphInstance;
	}

	public Map<String, Node> getAllNodeMap() {
		return allNodeMap;
	}

	public void setAllNodeMap(Map<String, Node> allNodeMap) {
		this.allNodeMap = allNodeMap;
	}

	public Map<String, Edge> getAllEdgeMap() {
		return allEdgeMap;
	}

	public void setAllEdgeMap(Map<String, Edge> allEdgeMap) {
		this.allEdgeMap = allEdgeMap;
	}

	public static void setTrainGraphInstance(TrainGraph trainGraphInstance) {
		TrainGraph.trainGraphInstance = trainGraphInstance;
	}
	public Map<String, AdjacencyNode> getAdjaListMap() {
		return adjaListMap;
	}
	
}
