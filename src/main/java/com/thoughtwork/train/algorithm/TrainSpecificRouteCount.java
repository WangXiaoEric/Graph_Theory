package com.thoughtwork.train.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.thoughtwork.train.model.Edge;
import com.thoughtwork.train.model.Graph;
import com.thoughtwork.train.util.StringsUtil;

public class TrainSpecificRouteCount implements SpecificRouteCount{
	
	
	public static Integer getMininalDistance(List<String> routeList, Graph graph) {
		Integer minNim = null;
		
		if(null != routeList && !routeList.isEmpty()) {
			for(String route : routeList) {
				Integer distance = TrainSpecificRouteCount.distanceCal(StringsUtil.addDash(route), graph);
				if(null == minNim || distance < minNim) {
					minNim = distance;
				}
			}
		}
		return minNim;
	}
	
	public static String distanceCalWrapper(String route, Graph graph) {
		Integer res = TrainSpecificRouteCount.distanceCal(route, graph);
		if(-1 == res) {
			return "NO SUCH ROUTE";
		}else {
			return res.toString();
		}
	}
	
	
	public static Integer distanceCal(String route, Graph graph) {
		//TODO Validation
		Integer distance = 0;
		List<String> routList = Arrays.asList(route.replaceAll(" ", "").split("‐"));
		for(Integer index = 0; index < routList.size()-1; index ++) {
			String trackIndex = routList.get(index) + routList.get(index+1);
			if(!graph.getAllEdgeMap().containsKey(trackIndex)) {
				return -1;
			}else {
				distance = distance + graph.getAllEdgeMap().get(trackIndex).getDistance();
			}
		}
		return distance;
	}
	
	public static Integer durationCal(String route, Graph graph) {
		//TODO Validation
		Integer duration = 0;
		List<String> routList = Arrays.asList(route.replaceAll(" ", "").split("‐"));
		for(Integer index = 0; index < routList.size(); index ++) {
			String trackIndex = routList.get(index) + routList.get(index+1);
			if(!graph.getAllEdgeMap().containsKey(trackIndex)) {
				return -1;
			}else {
				duration = duration + graph.getAllEdgeMap().get(trackIndex).getTrackTime();
			}
		}
		return duration;
	}
}
