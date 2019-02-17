package com.thoughtwork.train.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.thoughtwork.train.model.AdjacencyNode;
import com.thoughtwork.train.model.Edge;

public class AdjacencyTool {
	
	/**
	 * 获取邻接表最后一个节点
	 * @param node
	 * @return
	 */
	public static  AdjacencyNode getLast(AdjacencyNode node) {
		while(node.getNextNode() != null) {
			return AdjacencyTool.getLast(node.getNextNode());
		}
		return node;
	}
	
	/**
	 * 不允许重复
	 * @param adjaListMap
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> findRoutesByDeepFirstSearch(Map<String, AdjacencyNode> adjaListMap, String start, String end) {
		List<String> resRoutes = new ArrayList<String>();
		
		Map<String, Boolean> stationStates = new HashMap<String, Boolean>();
        Stack<String> stationStack = new Stack<String>();
        for(String stationName : adjaListMap.keySet()) {
        	stationStates.put(stationName, false);
        }
		
        String top;
        
        //Top节点已经访问过的邻结点
        String adjOfTop = null;
        
        stationStack.push(start);
        stationStates.put(start, true);
        
        while(!stationStack.isEmpty()) {
        	top = stationStack.peek();
        	
        	//第二个条件为了避免 C->C（自己到自己）情况
        	if(top.equals(end) && stationStack.size() != 1) {
        		String route = AdjacencyTool.printPath(stationStack);
        		resRoutes.add(route);
        		adjOfTop = stationStack.pop();
        	} else {
        		String nextNode = AdjacencyTool.getNextNode(adjaListMap.get(top), adjOfTop);
        		//如果栈中包含此节点，或者此节点为null 则无效
        		if((stationStack.contains(nextNode) && !stationStack.get(0).equals(nextNode)) || null == nextNode) {
        			adjOfTop = stationStack.pop();
        			stationStates.put(adjOfTop, false);
        			
        		//否则为有效节点
        		} else {
        			stationStack.push(nextNode);
        			adjOfTop = nextNode;
        			//考虑到自己指向自己的情况，如果是两个不同节点则重置adjOfTop节点
        			if(!stationStack.get(0).equals(nextNode)) {
        				adjOfTop = null;
        			}
        			
        		}
        	}
        }
		return resRoutes;
	}
	
	/**
	 * 中间元素允许重复, 并且限制了重复次数
	 * @param adjaListMap
	 * @param start
	 * @param end
	 * @param stopNum
	 * @return
	 */
	public static List<String> findRoutesByDeepFirstSearchRepetitive(Map<String, AdjacencyNode> adjaListMap, String start, String end, Integer stopNum) {
		List<String> resRoutes = new ArrayList<String>();
		
		Map<String, Boolean> stationStates = new HashMap<String, Boolean>();
        Stack<String> stationStack = new Stack<String>();
        for(String stationName : adjaListMap.keySet()) {
        	stationStates.put(stationName, false);
        }
		
        String top;
        
        //Top节点已经访问过的邻结点
        String adjOfTop = null;
        
        stationStack.push(start);
        stationStates.put(start, true);
        
        while(!stationStack.isEmpty()) {
        	top = stationStack.peek();
        	
        	//第二个条件为了避免 C->C（自己到自己）情况
        	if(top.equals(end) && stationStack.size() != 1 && stationStack.size() == stopNum + 1) {
        		String route = AdjacencyTool.printPath(stationStack);
        		resRoutes.add(route);
        		adjOfTop = stationStack.pop();
        	} else {
        		String nextNode = AdjacencyTool.getNextNode(adjaListMap.get(top), adjOfTop);
        		//如果栈中包含此节点，或者此节点为null 则无效
        		if(null == nextNode || stationStack.size() >= stopNum + 1) {
        			adjOfTop = stationStack.pop();
        			stationStates.put(adjOfTop, false);
        			
        		//否则为有效节点
        		} else {
        			stationStack.push(nextNode);
        			adjOfTop = nextNode;
        			//考虑到自己指向自己的情况，如果是两个不同节点则重置adjOfTop节点
        			if(!stationStack.get(0).equals(nextNode)) {
        				adjOfTop = null;
        			}
        			
        		}
        	}
        }
		return resRoutes;
	}
	
	/**
	 * 中间元素允许重复, 并且限制了最长距离
	 * @param adjaListMap
	 * @param start
	 * @param end
	 * @param stopNum
	 * @return
	 */
	public static List<String> findRoutesWithMaximalDistance(Map<String, AdjacencyNode> adjaListMap, String start, String end, Integer maximalDistance, Map<String, Edge> allEdgeMap) {
		List<String> resRoutes = new ArrayList<String>();
		
		Map<String, Boolean> stationStates = new HashMap<String, Boolean>();
        Stack<String> stationStack = new Stack<String>();
        for(String stationName : adjaListMap.keySet()) {
        	stationStates.put(stationName, false);
        }
		
        String top;
        Integer currentDistance = 0;
        //Top节点已经访问过的邻结点
        String adjOfTop = null;
        
        stationStack.push(start);
        stationStates.put(start, true);
        
        while(!stationStack.isEmpty()) {
        	top = stationStack.peek();
        	
        	//第二个条件为了避免 C->C（自己到自己）情况
        	if(top.equals(end) && stationStack.size() != 1 && currentDistance < maximalDistance && !resRoutes.contains(AdjacencyTool.printPath(stationStack))) {
        		String route = AdjacencyTool.printPath(stationStack);
        		resRoutes.add(route);
        		adjOfTop = null;
        		/*if(!stationStack.isEmpty()) {
        			currentDistance = currentDistance - allEdgeMap.get(stationStack.peek() + adjOfTop).getDistance();
        		}*/
        		
        	} else {
        		String nextNode = AdjacencyTool.getNextNode(adjaListMap.get(top), adjOfTop);
        		//如果栈中包含此节点，或者此节点为null 则无效
        		if(null == nextNode || currentDistance >= maximalDistance) {
        			adjOfTop = stationStack.pop();
        			if(!stationStack.isEmpty()) {
        				currentDistance = currentDistance - allEdgeMap.get(stationStack.peek() + adjOfTop).getDistance();
        			}
        			stationStates.put(adjOfTop, false);
        			
        		//否则为有效节点
        		} else {
        			
        			currentDistance = currentDistance + allEdgeMap.get(stationStack.peek() + nextNode).getDistance();
        			stationStack.push(nextNode);
        			
        			
        			//adjOfTop = nextNode;
        			//考虑到自己指向自己的情况，如果是两个不同节点则重置adjOfTop节点
        			//if(!stationStack.get(0).equals(nextNode)) {
        				adjOfTop = null;
        			//}
        			
        		}
        	}
        }
		return resRoutes;
	}
	
	private static String getNextNode(AdjacencyNode node, String current) {
		AdjacencyNode nextNode = node.getNextNode();
		if(current == null) {
			if(nextNode == null) {
				return null;
			} else {
				return nextNode.getNodeIndex();
			}
		} else {
			while(null != nextNode) {
				
				if(nextNode.getNodeIndex().equals(current)) {
					if(nextNode.getNextNode() == null) {
						return null;
					} else {
						return nextNode.getNextNode().getNodeIndex();
					}
				}
				nextNode = nextNode.getNextNode();
			}
		}
		return null;
	}
	
	private static String printPath(Stack<String> nodeStack){
		StringBuilder res = new StringBuilder();
		for(String node : nodeStack){
			res.append(node);
		}
		return res.toString();
	}
}
